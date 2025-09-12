package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.ProfileBean;


public interface ProfileListener {

    void onLoadCompleted(ProfileBean profileBean);

    void onLoadFailed(String error);
}
