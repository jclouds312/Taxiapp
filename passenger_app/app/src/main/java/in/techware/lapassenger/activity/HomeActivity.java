package in.techware.lapassenger.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.HashMap;

import in.techware.lapassenger.R;
import in.techware.lapassenger.model.RideBean;
import in.techware.lapassenger.net.ServiceNames;
import in.techware.lapassenger.net.WebConnector;
import in.techware.lapassenger.util.Constants;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;
    
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button btnBookRide;
    private LinearLayout layoutRideInProgress;
    private TextView tvRideStatus;
    private TextView tvDriverName;
    private Button btnTrackRide;
    private Button btnCancelRide;
    
    private SharedPreferences preferences;
    private RideBean currentRide;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        preferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        
        initViews();
        setupMap();
        setupNavigationDrawer();
        getCurrentLocation();
        checkActiveRide();
    }
    
    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        btnBookRide = findViewById(R.id.btn_book_ride);
        layoutRideInProgress = findViewById(R.id.layout_ride_in_progress);
        tvRideStatus = findViewById(R.id.tv_ride_status);
        tvDriverName = findViewById(R.id.tv_driver_name);
        btnTrackRide = findViewById(R.id.btn_track_ride);
        btnCancelRide = findViewById(R.id.btn_cancel_ride);
        
        btnBookRide.setOnClickListener(v -> {
            if (currentLocation != null) {
                Intent intent = new Intent(HomeActivity.this, BookRideActivity.class);
                intent.putExtra("pickup_lat", currentLocation.getLatitude());
                intent.putExtra("pickup_lng", currentLocation.getLongitude());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Getting your location...", Toast.LENGTH_SHORT).show();
                getCurrentLocation();
            }
        });
        
        btnTrackRide.setOnClickListener(v -> {
            if (currentRide != null) {
                Intent intent = new Intent(HomeActivity.this, TrackRideActivity.class);
                intent.putExtra("ride", currentRide);
                startActivity(intent);
            }
        });
        
        btnCancelRide.setOnClickListener(v -> cancelRide());
    }
    
    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }
    
    private void setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            
            if (id == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(HomeActivity.this, TripHistoryActivity.class));
            } else if (id == R.id.nav_payment) {
                startActivity(new Intent(HomeActivity.this, PaymentMethodsActivity.class));
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            } else if (id == R.id.nav_help) {
                startActivity(new Intent(HomeActivity.this, HelpActivity.class));
            } else if (id == R.id.nav_logout) {
                logout();
            }
            
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        
        // Update nav header with user info
        View headerView = navigationView.getHeaderView(0);
        TextView tvNavName = headerView.findViewById(R.id.tv_nav_name);
        TextView tvNavPhone = headerView.findViewById(R.id.tv_nav_phone);
        
        tvNavName.setText(preferences.getString(Constants.USER_NAME, "User"));
        tvNavPhone.setText(preferences.getString(Constants.USER_PHONE, ""));
    }
    
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        
        // Set default location (Buenos Aires, Argentina)
        LatLng defaultLocation = new LatLng(-34.6037, -58.3816);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, Constants.DEFAULT_ZOOM));
    }
    
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.REQUEST_LOCATION_PERMISSION);
            return;
        }
        
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        currentLocation = location;
                        updateMapLocation(location);
                    }
                });
    }
    
    private void updateMapLocation(Location location) {
        if (mMap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.DEFAULT_ZOOM));
        }
    }
    
    private void checkActiveRide() {
        String userId = preferences.getString(Constants.USER_ID, "");
        
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", userId);
        
        new WebConnector(this, ServiceNames.GET_RIDE_STATUS, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                try {
                    Gson gson = new Gson();
                    RideResponse rideResponse = gson.fromJson(response, RideResponse.class);
                    
                    if (rideResponse != null && rideResponse.status && rideResponse.data != null) {
                        currentRide = rideResponse.data;
                        showRideInProgress(currentRide);
                    } else {
                        showBookRideButton();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showBookRideButton();
                }
            }
            
            @Override
            public void onError(String error) {
                showBookRideButton();
            }
        }).execute();
    }
    
    private void showRideInProgress(RideBean ride) {
        btnBookRide.setVisibility(View.GONE);
        layoutRideInProgress.setVisibility(View.VISIBLE);
        
        tvRideStatus.setText("Status: " + ride.getStatus());
        tvDriverName.setText("Driver: " + ride.getDriverName());
    }
    
    private void showBookRideButton() {
        btnBookRide.setVisibility(View.VISIBLE);
        layoutRideInProgress.setVisibility(View.GONE);
    }
    
    private void cancelRide() {
        if (currentRide == null) return;
        
        HashMap<String, String> params = new HashMap<>();
        params.put("ride_id", currentRide.getRideId());
        params.put("user_id", preferences.getString(Constants.USER_ID, ""));
        
        new WebConnector(this, ServiceNames.CANCEL_RIDE, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(HomeActivity.this, "Ride cancelled", Toast.LENGTH_SHORT).show();
                currentRide = null;
                showBookRideButton();
            }
            
            @Override
            public void onError(String error) {
                Toast.makeText(HomeActivity.this, "Failed to cancel ride", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }
    
    private void logout() {
        preferences.edit().clear().apply();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == Constants.REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        checkActiveRide();
    }
    
    // Response class
    private static class RideResponse {
        boolean status;
        String message;
        RideBean data;
    }
}