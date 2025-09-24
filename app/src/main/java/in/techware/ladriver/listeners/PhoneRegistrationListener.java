package in.techware.ladriver.listeners;


import in.techware.ladriver.model.AuthBean;

public interface PhoneRegistrationListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
