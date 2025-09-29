package in.techware.ladriver.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import in.techware.ladriver.R;
import in.techware.ladriver.dialogs.PopupMessage;
import in.techware.ladriver.listeners.LoginListener;
import in.techware.ladriver.listeners.PhoneRegistrationListener;
import in.techware.ladriver.model.AuthBean;
import in.techware.ladriver.model.RegistrationBean;
import in.techware.ladriver.net.DataManager;
import in.techware.ladriver.util.AppConstants;
import in.techware.ladriver.widgets.OTPEditText;

public class MobileVerificationActivity extends BaseAppCompatActivity {

    private RegistrationBean registrationBean;
    private Toolbar toolbar;
    private OTPEditText etxtOne;
    private OTPEditText etxtTwo;
    private OTPEditText etxtThree;
    private OTPEditText etxtFour;
    private OTPEditText etxtFive;
    private OTPEditText etxtSix;
    private String phone, email, password, name;
    private boolean isLogin;
    private TextView txtResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        registrationBean = (RegistrationBean) getIntent().getSerializableExtra("bean");
        isLogin = getIntent().getBooleanExtra("is_login", false);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isLogin) {
            etxtSix.setFocusable(true);
            etxtSix.requestFocus();
            phone = registrationBean.getPhone();
            password = registrationBean.getPassword();
        } else {

            phone = registrationBean.getPhone();
            email = registrationBean.getEmail();
            name = registrationBean.getName();
            password = registrationBean.getPassword();
        }

    }

    private void initViews() {

        coordinatorLayout.removeView(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar_mobile_verification);
        setSupportActionBar(toolbar);

        etxtOne = (OTPEditText) findViewById(R.id.etxt_mobile_verification_one);
        etxtTwo = (OTPEditText) findViewById(R.id.etxt_mobile_verification_two);
        etxtThree = (OTPEditText) findViewById(R.id.etxt_mobile_verification_three);
        etxtFour = (OTPEditText) findViewById(R.id.etxt_mobile_verification_four);
        etxtFive = (OTPEditText) findViewById(R.id.etxt_mobile_verification_five);
        etxtSix = (OTPEditText) findViewById(R.id.etxt_mobile_verification_six);

        txtResend = (TextView) findViewById(R.id.txt_mobile_verification_resend);

    }

    public void onMobileVerificationResendCodeClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        if (isLogin) {
            performLogin();
        } else {
            performPhoneRegistration();
        }

    }

    public void onMobileVerificationSubmitClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //        mVibrator.vibrate(25);

        if (collectOTP().length() == 6) {
            performOTPSubmit();
        } else {
            Snackbar.make(coordinatorLayout, R.string.message_otp_length_error, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
        }

    }

    private void performOTPSubmit() {

        swipeView.setRefreshing(true);
        JSONObject postData = getOTPSubmitJSObj();

        DataManager.performOTPSubmit(postData, new LoginListener() {

            @Override
            public void onLoadCompleted(AuthBean authBean) {
                swipeView.setRefreshing(false);
                App.saveToken(authBean);
                startActivity(new Intent(MobileVerificationActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

            }
        });
    }

    private JSONObject getOTPSubmitJSObj() {
        JSONObject postData = new JSONObject();
        try {
            postData.put("phone", phone);
            postData.put("otp", collectOTP());
            postData.put("is_login", isLogin);

            if (isLogin) {
                postData.put("password", password);
            } else {
                postData.put("name", name);
                postData.put("email", email);
                postData.put("password", password);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }


    private String collectOTP() {

        String otp = "";
        otp = etxtOne.getText().toString() + etxtTwo.getText().toString() + etxtThree.getText().toString()
                + etxtFour.getText().toString() + etxtFive.getText().toString() + etxtSix.getText().toString();

        return otp;
    }

    private void performPhoneRegistration() {
        swipeView.setRefreshing(true);
        JSONObject postData = getPhoneRegistrationJSObj();

        DataManager.performPhoneRegistration(postData, new PhoneRegistrationListener() {

            @Override
            public void onLoadCompleted(RegistrationBean registrationBean) {
                swipeView.setRefreshing(false);
                PopupMessage popupMessage = new PopupMessage(MobileVerificationActivity.this);
                popupMessage.show(getString(R.string.message_otp_sent_to_your_phone), getString(R.string.btn_ok));

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

            }
        });
    }

    private JSONObject getPhoneRegistrationJSObj() {
        JSONObject postData = new JSONObject();
        try {

            postData.put("phone", phone);
            postData.put("email", email);
            postData.put("name", name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    private void performLogin() {

        swipeView.setRefreshing(true);
        JSONObject postData = getLoginJSObj();

        DataManager.performLogin(postData, new PhoneRegistrationListener() {
            @Override
            public void onLoadCompleted(RegistrationBean registrationBean) {
                swipeView.setRefreshing(false);

                PopupMessage popupMessage = new PopupMessage(MobileVerificationActivity.this);
                popupMessage.show(getString(R.string.message_otp_sent_to_your_phone), getString(R.string.btn_ok));

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        });
    }

    private JSONObject getLoginJSObj() {
        JSONObject postData = new JSONObject();

        try {
            postData.put("phone", phone);
            postData.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (collectOTP().length() == 6) {
                performOTPSubmit();
            } else {
                Snackbar.make(coordinatorLayout, R.string.message_otp_length_error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        }
        return false;
    }

}
