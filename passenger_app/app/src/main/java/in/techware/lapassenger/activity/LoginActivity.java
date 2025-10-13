package in.techware.lapassenger.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;

import in.techware.lapassenger.R;
import in.techware.lapassenger.model.UserBean;
import in.techware.lapassenger.net.ServiceNames;
import in.techware.lapassenger.net.WebConnector;
import in.techware.lapassenger.util.Constants;

public class LoginActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvForgotPassword;
    private ProgressBar progressBar;
    
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        
        initViews();
        setListeners();
    }

    private void initViews() {
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setListeners() {
        btnLogin.setOnClickListener(v -> validateAndLogin());
        
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        
        tvForgotPassword.setOnClickListener(v -> {
            // TODO: Implement forgot password
            Toast.makeText(this, "Forgot password coming soon", Toast.LENGTH_SHORT).show();
        });
    }

    private void validateAndLogin() {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(phone)) {
            etPhone.setError("Enter phone number");
            return;
        }
        
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter password");
            return;
        }
        
        performLogin(phone, password);
    }

    private void performLogin(String phone, String password) {
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
        
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        params.put("fcm_token", preferences.getString(Constants.FCM_TOKEN, ""));
        params.put("device_type", "android");
        
        new WebConnector(this, ServiceNames.LOGIN, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                
                try {
                    Gson gson = new Gson();
                    LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                    
                    if (loginResponse != null && loginResponse.status) {
                        // Save user data
                        UserBean user = loginResponse.data;
                        preferences.edit()
                                .putBoolean(Constants.IS_LOGGED_IN, true)
                                .putString(Constants.USER_ID, user.getUserId())
                                .putString(Constants.USER_NAME, user.getName())
                                .putString(Constants.USER_PHONE, user.getPhone())
                                .putString(Constants.USER_EMAIL, user.getEmail())
                                .putString(Constants.AUTH_TOKEN, user.getAuthToken())
                                .apply();
                        
                        // Navigate to home
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        String message = loginResponse != null ? loginResponse.message : "Login failed";
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
            
            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Connection error: " + error, Toast.LENGTH_LONG).show();
            }
        }).execute();
    }
    
    // Response classes
    private static class LoginResponse {
        boolean status;
        String message;
        UserBean data;
    }
}