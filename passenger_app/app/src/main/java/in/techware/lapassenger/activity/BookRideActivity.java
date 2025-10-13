package in.techware.lapassenger.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.techware.lapassenger.R;
import in.techware.lapassenger.model.RideBean;
import in.techware.lapassenger.net.ServiceNames;
import in.techware.lapassenger.net.WebConnector;
import in.techware.lapassenger.util.Constants;

public class BookRideActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText etPickupLocation;
    private EditText etDropLocation;
    private RadioGroup rgVehicleType;
    private RadioButton rbSedan;
    private RadioButton rbSuv;
    private RadioButton rbHatchback;
    private TextView tvEstimatedFare;
    private TextView tvEstimatedTime;
    private Button btnConfirmBooking;
    private ProgressBar progressBar;
    private LinearLayout layoutFareDetails;
    
    private double pickupLat;
    private double pickupLng;
    private double dropLat;
    private double dropLng;
    private String pickupAddress = "";
    private String dropAddress = "";
    private String selectedVehicleType = "sedan";
    private String estimatedFare = "0";
    
    private SharedPreferences preferences;
    private Geocoder geocoder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ride);
        
        preferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        geocoder = new Geocoder(this, Locale.getDefault());
        
        // Get pickup location from intent
        pickupLat = getIntent().getDoubleExtra("pickup_lat", 0);
        pickupLng = getIntent().getDoubleExtra("pickup_lng", 0);
        
        initViews();
        setupMap();
        getAddressFromLocation(pickupLat, pickupLng, true);
    }
    
    private void initViews() {
        etPickupLocation = findViewById(R.id.et_pickup_location);
        etDropLocation = findViewById(R.id.et_drop_location);
        rgVehicleType = findViewById(R.id.rg_vehicle_type);
        rbSedan = findViewById(R.id.rb_sedan);
        rbSuv = findViewById(R.id.rb_suv);
        rbHatchback = findViewById(R.id.rb_hatchback);
        tvEstimatedFare = findViewById(R.id.tv_estimated_fare);
        tvEstimatedTime = findViewById(R.id.tv_estimated_time);
        btnConfirmBooking = findViewById(R.id.btn_confirm_booking);
        progressBar = findViewById(R.id.progress_bar);
        layoutFareDetails = findViewById(R.id.layout_fare_details);
        
        etDropLocation.setOnClickListener(v -> {
            Intent intent = new Intent(BookRideActivity.this, SelectDestinationActivity.class);
            startActivityForResult(intent, Constants.REQUEST_SELECT_LOCATION);
        });
        
        rgVehicleType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_sedan) {
                selectedVehicleType = "sedan";
            } else if (checkedId == R.id.rb_suv) {
                selectedVehicleType = "suv";
            } else if (checkedId == R.id.rb_hatchback) {
                selectedVehicleType = "hatchback";
            }
            
            if (dropLat != 0 && dropLng != 0) {
                estimateFare();
            }
        });
        
        btnConfirmBooking.setOnClickListener(v -> confirmBooking());
    }
    
    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        // Add pickup marker
        LatLng pickupLatLng = new LatLng(pickupLat, pickupLng);
        mMap.addMarker(new MarkerOptions()
                .position(pickupLatLng)
                .title("Pickup Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pickupLatLng, 14));
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == Constants.REQUEST_SELECT_LOCATION && resultCode == RESULT_OK) {
            if (data != null) {
                dropLat = data.getDoubleExtra("lat", 0);
                dropLng = data.getDoubleExtra("lng", 0);
                dropAddress = data.getStringExtra("address");
                
                etDropLocation.setText(dropAddress);
                
                // Add drop marker on map
                if (mMap != null) {
                    LatLng dropLatLng = new LatLng(dropLat, dropLng);
                    mMap.addMarker(new MarkerOptions()
                            .position(dropLatLng)
                            .title("Drop Location")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    
                    // Zoom to show both markers
                    // TODO: Implement bounds to show both markers
                }
                
                estimateFare();
            }
        }
    }
    
    private void getAddressFromLocation(double lat, double lng, boolean isPickup) {
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressText = address.getAddressLine(0);
                
                if (isPickup) {
                    pickupAddress = addressText;
                    etPickupLocation.setText(addressText);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void estimateFare() {
        progressBar.setVisibility(View.VISIBLE);
        
        HashMap<String, String> params = new HashMap<>();
        params.put("pickup_lat", String.valueOf(pickupLat));
        params.put("pickup_lng", String.valueOf(pickupLng));
        params.put("drop_lat", String.valueOf(dropLat));
        params.put("drop_lng", String.valueOf(dropLng));
        params.put("vehicle_type", selectedVehicleType);
        
        new WebConnector(this, ServiceNames.ESTIMATE_FARE, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                progressBar.setVisibility(View.GONE);
                
                try {
                    Gson gson = new Gson();
                    FareResponse fareResponse = gson.fromJson(response, FareResponse.class);
                    
                    if (fareResponse != null && fareResponse.status) {
                        estimatedFare = fareResponse.estimated_fare;
                        tvEstimatedFare.setText("$" + estimatedFare);
                        tvEstimatedTime.setText(fareResponse.estimated_time + " mins");
                        layoutFareDetails.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
            }
        }).execute();
    }
    
    private void confirmBooking() {
        if (dropLat == 0 || dropLng == 0) {
            Toast.makeText(this, "Please select drop location", Toast.LENGTH_SHORT).show();
            return;
        }
        
        progressBar.setVisibility(View.VISIBLE);
        btnConfirmBooking.setEnabled(false);
        
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", preferences.getString(Constants.USER_ID, ""));
        params.put("pickup_lat", String.valueOf(pickupLat));
        params.put("pickup_lng", String.valueOf(pickupLng));
        params.put("pickup_address", pickupAddress);
        params.put("drop_lat", String.valueOf(dropLat));
        params.put("drop_lng", String.valueOf(dropLng));
        params.put("drop_address", dropAddress);
        params.put("vehicle_type", selectedVehicleType);
        params.put("estimated_fare", estimatedFare);
        params.put("payment_method", Constants.PAYMENT_CASH);
        
        new WebConnector(this, ServiceNames.REQUEST_RIDE, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                progressBar.setVisibility(View.GONE);
                
                try {
                    Gson gson = new Gson();
                    RideResponse rideResponse = gson.fromJson(response, RideResponse.class);
                    
                    if (rideResponse != null && rideResponse.status) {
                        Toast.makeText(BookRideActivity.this, "Ride booked successfully", Toast.LENGTH_SHORT).show();
                        
                        // Navigate to tracking
                        Intent intent = new Intent(BookRideActivity.this, TrackRideActivity.class);
                        intent.putExtra("ride", rideResponse.data);
                        startActivity(intent);
                        finish();
                    } else {
                        String message = rideResponse != null ? rideResponse.message : "Booking failed";
                        Toast.makeText(BookRideActivity.this, message, Toast.LENGTH_LONG).show();
                        btnConfirmBooking.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(BookRideActivity.this, "Error processing response", Toast.LENGTH_SHORT).show();
                    btnConfirmBooking.setEnabled(true);
                }
            }
            
            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                btnConfirmBooking.setEnabled(true);
                Toast.makeText(BookRideActivity.this, "Connection error", Toast.LENGTH_LONG).show();
            }
        }).execute();
    }
    
    // Response classes
    private static class FareResponse {
        boolean status;
        String message;
        String estimated_fare;
        String estimated_time;
        String distance;
    }
    
    private static class RideResponse {
        boolean status;
        String message;
        RideBean data;
    }
}