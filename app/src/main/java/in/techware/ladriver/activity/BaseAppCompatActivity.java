package in.techware.ladriver.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import in.techware.ladriver.R;
import in.techware.ladriver.app.App;
import in.techware.ladriver.config.Config;
import in.techware.ladriver.listeners.BasicListener;
import in.techware.ladriver.listeners.ProfileListener;
import in.techware.ladriver.model.BasicBean;
import in.techware.ladriver.model.ProfileBean;
import in.techware.ladriver.net.DataManager;
import in.techware.ladriver.widgets.CustomTextView;

public class BaseAppCompatActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationButtonClickListener, com.google.android.gms.location.LocationListener,
        LocationListener {
    private static final String TAG = "BaseAppCompatActivity";

    static final int HOME_ACTIVITY = 1;

    private static int CURRENT_ACTIVITY = 0;


    protected LocationListener locationListener;
    private static final LocationRequest mLocationRequest = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    FrameLayout lytContent;
    NavigationView leftDrawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    final Handler mHandler = new Handler();

    protected SwipeRefreshLayout swipeView;
    protected CoordinatorLayout coordinatorLayout;
    protected Toolbar toolbar;

    private MenuItem menuProgress;
    private View lytDrawer;
    private View lytDrawerHeader;
    private View lytProgress;
    private ProgressBar progressBase;
    private View lytMessage;
    private CustomTextView txtMessage;
    private ImageView ivMessage;

    private ImageView ivUserDP;
    private ProfileBean profileBean;
    private CustomTextView txtName;
    private CustomTextView txtPhone;
    private CustomTextView txtEmail;


    private GoogleApiClient mGoogleApiClient;
    private boolean isDriverLocationUpdated;

    private void initViewBase() {

        initBase();

        initLocationPermissionCheck();

        if (App.getInstance().getGoogleApiClient() == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(App.getInstance().getApplicationContext())
                    .addApi(LocationServices.API)
                    .enableAutoManage(this, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

            App.getInstance().setGoogleApiClient(mGoogleApiClient);
        } else {
            mGoogleApiClient = App.getInstance().getGoogleApiClient();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        leftDrawer = (NavigationView) findViewById(R.id.navigation_view_base_appcompat);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_appcompat);
        lytContent = (FrameLayout) findViewById(R.id.lyt_contents_appcompat);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout_base_appcompat);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leftDrawer.setPadding(0, 0, 0, 0);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        lytProgress = findViewById(R.id.lyt_progress_base_appcompat);
        progressBase = (ProgressBar) findViewById(R.id.progress_base_appcompat);

        lytMessage = findViewById(R.id.lyt_default_message_base_appcompat);
        txtMessage = (CustomTextView) findViewById(R.id.txt_default_message_base_appcompat);
        ivMessage = (ImageView) findViewById(R.id.iv_default_message_base_appcompat);

        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_base_appcompat);
        swipeView.setColorSchemeResources(R.color.holo_blue_dark, R.color.holo_blue_light,
                R.color.holo_green_light, R.color.holo_orange_light);
        swipeView.setEnabled(false);
        swipeView.setProgressViewOffset(false, 0,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()) + mActionBarHeight));

        FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams param2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param1.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, r.getDisplayMetrics());
        param2.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 155, r.getDisplayMetrics());

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.btn_open, R.string.btn_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(mDrawerToggle);
        drawerLayout.setDrawerShadow(R.drawable.shadow,
                GravityCompat.START);

        setLeftDrawer();
        setUser();

        setMessageScreenVisibility(false, false, R.drawable.logo_splash, getString(R.string.label_nothing_to_show));
        setProgressScreenVisibility(false, false);

        mHandler.postDelayed(periodicTask, 3000);

    }

    public void onToolbarHomeClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        if (!drawerLayout.isDrawerOpen(leftDrawer)) {
            drawerLayout.openDrawer(leftDrawer);
        } else {
            drawerLayout.closeDrawer(leftDrawer);
        }
    }

    public void onToolbarSearchClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

    }

    private void initLocationPermissionCheck() {
        if (CURRENT_ACTIVITY == HOME_ACTIVITY) {
            if (!checkForLocationPermissions())
                getLocationPermissions();
            checkLocationSettingsStatus();
        }
    }

    private final Runnable periodicTask = new Runnable() {
        @Override
        public void run() {

            if (checkForLocationPermissions()) {
                getCurrentLocation();
            } else {
                getLocationPermissions();
                checkLocationSettingsStatus();
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void onResume() {
        super.onResume();

        drawerLayout.closeDrawers();

        if (Config.getInstance().getAuthToken() != null && !Config.getInstance().getAuthToken().equals("")) {
            if (App.isNetworkAvailable()) {
                fetchProfile();
            }
        }
    }

    @Override
    public void setContentView(final int layoutResID) {
        DrawerLayout lytBase = (DrawerLayout) getLayoutInflater().inflate(R.layout.layout_base_appcompat, null);
        lytContent = (FrameLayout) lytBase.findViewById(R.id.lyt_contents_appcompat);
        getLayoutInflater().inflate(layoutResID, lytContent, true);
        super.setContentView(lytBase);
        initViewBase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                if (drawerLayout.isDrawerOpen(leftDrawer))
                    drawerLayout.closeDrawer(leftDrawer);
                else if (!drawerLayout.isDrawerOpen(leftDrawer))
                    drawerLayout.openDrawer(leftDrawer);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setLeftDrawer() {

        LayoutInflater inflater = getLayoutInflater();
        lytDrawer = inflater.inflate(R.layout.layout_drawer, null);

        ivUserDP = (ImageView) lytDrawer.findViewById(R.id.iv_drawer_profile_photo);
        txtName = (CustomTextView) lytDrawer.findViewById(R.id.txt_drawer_name);
        txtPhone = (CustomTextView) lytDrawer.findViewById(R.id.txt_drawer_phone);
        txtEmail = (CustomTextView) lytDrawer.findViewById(R.id.txt_drawer_email);

        addLoginDrawerToNavigationView();

    }

    private void addLoginDrawerToNavigationView() {
        leftDrawer.addView(lytDrawer);
    }

    private void removeLoginDrawerFromNavigationView() {
        leftDrawer.removeView(lytDrawer);
    }

    void setUser() {

        txtName.setText(Config.getInstance().getName());
        txtEmail.setText(Config.getInstance().getEmail());
        txtPhone.setText(Config.getInstance().getPhone());

        Log.i(TAG, "setUser: Profile Photo : " + Config.getInstance().getProfilePhoto());

        Glide.with(getApplicationContext())
                .load(Config.getInstance().getProfilePhoto())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_profile_photo_default)
                        .fallback(R.drawable.ic_profile_photo_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .circleCrop())
                .into(ivUserDP);

    }


    public void onLogoutClick(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        App.logout();
        startActivity(new Intent(this, SplashActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();

    }


    public void onDrawerProfileEditClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        startActivity(new Intent(BaseAppCompatActivity.this, ProfileActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

    }

    public void onDrawerAboutUsClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


    }

    public void onDrawerHelpClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


    }

    public void onDrawerShareAppClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

    }

    public void onDrawerFeedbackClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


    }

    public void onDrawerSettingsClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


    }

    private void fetchProfile() {

        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("auth_token", Config.getInstance().getAuthToken());

        Log.i(TAG, "fetchProfile: ID " + Config.getInstance().getUserID());
        Log.i(TAG, "fetchProfile: Name " + Config.getInstance().getFirstName() + " " + Config.getInstance().getLastName());

        DataManager.fetchProfile(urlParams, new ProfileListener() {
            @Override
            public void onLoadCompleted(ProfileBean profileBean) {
                System.out.println("Successful  : ProfileBean : " + profileBean);
                BaseAppCompatActivity.this.profileBean = profileBean;
                populateProfile(profileBean);
                App.saveToken(getApplicationContext());

            }

            @Override
            public void onLoadFailed(String errorMsg) {

            }
        });

    }

    private void populateProfile(ProfileBean profileBean) {

        try {
            Config.getInstance().setName(profileBean.getName());
            Config.getInstance().setFirstName(profileBean.getFirstName());
            Config.getInstance().setLastName(profileBean.getLastName());
            Config.getInstance().setProfilePhoto(profileBean.getProfilePhoto());
            Config.getInstance().setUserID(profileBean.getId());
            Config.getInstance().setPhone(profileBean.getPhone());
            Config.getInstance().setAddress(profileBean.getAddress());
            Config.getInstance().setEmail(profileBean.getEmail());
            setUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearApplicationData() {
        File cache = getApplication().getFilesDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                (new File(appDir, s)).delete();
            }
        }
    }

    protected void setMessageScreenVisibility(boolean isScreenVisible, boolean isImageVisible, int imagePath, String message) {
        if (isScreenVisible) {
            lytMessage.setVisibility(View.VISIBLE);
            txtMessage.setText(message);
            if (isImageVisible) {
                ivMessage.setVisibility(View.VISIBLE);
                ivMessage.setImageResource(imagePath);
            } else {
                ivMessage.setVisibility(View.GONE);
            }
        } else
            lytMessage.setVisibility(View.GONE);
    }

    protected void setProgressScreenVisibility(boolean isScreenVisible, boolean isProgressVisible) {
        if (isScreenVisible) {
            lytProgress.setVisibility(View.VISIBLE);
            if (isProgressVisible) {
                progressBase.setVisibility(View.VISIBLE);
            } else {
                progressBase.setVisibility(View.GONE);
            }
        } else
            lytProgress.setVisibility(View.GONE);
    }

    void setCurrentActivity(int currentActivity) {
        CURRENT_ACTIVITY = currentActivity;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(leftDrawer)) {
                drawerLayout.closeDrawer(leftDrawer);
            }
            else {
                onBackPressed();
            }
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }

    private void performDriverLocationUpdate(Location location) {

        JSONObject postData = getUpdateDriverLocationJSObj(location);

        DataManager.performUpdateDriverLocation(postData, new BasicListener() {
            @Override
            public void onLoadCompleted(BasicBean basicBean) {
                isDriverLocationUpdated = true;
            }

            @Override
            public void onLoadFailed(String error) {

            }
        });


    }

    private JSONObject getUpdateDriverLocationJSObj(Location location) {
        JSONObject postData = new JSONObject();

        try {
            postData.put("latitude", location.getLatitude());
            postData.put("longitude", location.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    private void getLocationName() {

        String currentLatitude = Config.getInstance().getCurrentLatitude();
        String currentLongitude = Config.getInstance().getCurrentLongitude();

        System.out.println("Current Location : " + currentLatitude + "," + currentLongitude);

        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("latlng", currentLatitude + "," + currentLongitude);
        urlParams.put("sensor", "true");

    }

    void setUpLocationClientIfNeeded() {

        if (App.getInstance().getGoogleApiClient() == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .enableAutoManage(this, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            App.getInstance().setGoogleApiClient(mGoogleApiClient);
        }
    }

    void getCurrentLocation() {
        setUpLocationClientIfNeeded();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!checkForLocationPermissions())
                getLocationPermissions();
        }

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            if (LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient) != null) {
                Config.getInstance().setCurrentLatitude(""
                        + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude());
                Config.getInstance().setCurrentLongitude(""
                        + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude());
            }

        }
        if ((Config.getInstance().getCurrentLatitude() == null || Config.getInstance().getCurrentLongitude() == null)
                || (Config.getInstance().getCurrentLatitude().equals("") || Config.getInstance().getCurrentLatitude().equals(""))) {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            }
        } 
    }

    @Override
    public void onLocationChanged(Location location) {

        if (Config.getInstance().isOnline()
                && (Config.getInstance().getCurrentLatitude() == null
                || Config.getInstance().getCurrentLongitude() == null
                || Config.getInstance().getCurrentLatitude().equalsIgnoreCase("")
                || Config.getInstance().getCurrentLongitude().equalsIgnoreCase("")
                || !Config.getInstance().getCurrentLatitude().equalsIgnoreCase(String.valueOf(location.getLatitude()))
                || !Config.getInstance().getCurrentLongitude().equalsIgnoreCase(String.valueOf(location.getLongitude()))
                || !isDriverLocationUpdated)) {

            Log.i(TAG, "onLocationChanged: Config : Latitude : " + Config.getInstance().getCurrentLatitude());
            Log.i(TAG, "onLocationChanged: Config : Longitude : " + Config.getInstance().getCurrentLongitude());
            Log.i(TAG, "onLocationChanged: Location Change : Latitude : " + location.getLatitude());
            Log.i(TAG, "onLocationChanged: Location Change : Longtitude : " + location.getLongitude());

            performDriverLocationUpdate(location);
        }

        Config.getInstance().setCurrentLatitude("" + location.getLatitude());
        Config.getInstance().setCurrentLongitude("" + location.getLongitude());
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult arg0) {
    }

    @Override
    public void onConnected(Bundle arg0) {
        try {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!checkForLocationPermissions())
                    getLocationPermissions();
                checkLocationSettingsStatus();
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onConnectionSuspended(int arg0) {

    }
}
