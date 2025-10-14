package in.techware.lapassenger.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessaging;

import in.techware.lapassenger.R;
import in.techware.lapassenger.util.Constants;

public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int SPLASH_DURATION = 2000; // 2 seconds
    
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);

        // Initialize FCM
        initializeFCM();

        // Check permissions
        if (checkPermissions()) {
            proceedToNextScreen();
        } else {
            requestPermissions();
        }
    }

    private void initializeFCM() {
        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    String token = task.getResult();
                    preferences.edit().putString(Constants.FCM_TOKEN, token).apply();
                }
            });
    }

    private boolean checkPermissions() {
        return ContextCompat.checkSelfPermission(this, 
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.POST_NOTIFICATIONS
                },
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && 
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                proceedToNextScreen();
            } else {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void proceedToNextScreen() {
        new Handler().postDelayed(() -> {
            boolean isLoggedIn = preferences.getBoolean(Constants.IS_LOGGED_IN, false);
            
            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(SplashActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}