package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.AuthBean;

public interface PhoneRegistrationListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
