package in.techware.lapassenger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import in.techware.lapassenger.net.ServiceNames;
import in.techware.lapassenger.net.WebConnector;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initViews();
        setListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(v -> validateAndRegister());
        
        tvLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void validateAndRegister() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter your name");
            return;
        }
        
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email");
            return;
        }
        
        if (TextUtils.isEmpty(phone) || phone.length() < 10) {
            etPhone.setError("Enter valid phone number");
            return;
        }
        
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            return;
        }
        
        performRegistration(name, email, phone, password);
    }

    private void performRegistration(String name, String email, String phone, String password) {
        progressBar.setVisibility(View.VISIBLE);
        btnRegister.setEnabled(false);
        
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("phone", phone);
        params.put("password", password);
        params.put("device_type", "android");
        
        new WebConnector(this, ServiceNames.REGISTRATION, params, new WebConnector.WebConnectorListener() {
            @Override
            public void onSuccess(String response) {
                progressBar.setVisibility(View.GONE);
                btnRegister.setEnabled(true);
                
                try {
                    Gson gson = new Gson();
                    RegisterResponse registerResponse = gson.fromJson(response, RegisterResponse.class);
                    
                    if (registerResponse != null && registerResponse.status) {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        
                        // Navigate to OTP verification
                        Intent intent = new Intent(RegisterActivity.this, OTPVerificationActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("user_id", registerResponse.user_id);
                        startActivity(intent);
                        finish();
                    } else {
                        String message = registerResponse != null ? registerResponse.message : "Registration failed";
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Error processing response", Toast.LENGTH_SHORT).show();
                }
            }
            
            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                btnRegister.setEnabled(true);
                Toast.makeText(RegisterActivity.this, "Connection error: " + error, Toast.LENGTH_LONG).show();
            }
        }).execute();
    }
    
    // Response class
    private static class RegisterResponse {
        boolean status;
        String message;
        String user_id;
    }
}