package in.techware.ladriver.listeners;


import in.techware.ladriver.model.ProfileBean;


public interface ProfileListener {

    void onLoadCompleted(ProfileBean profileBean);

    void onLoadFailed(String error);
}
