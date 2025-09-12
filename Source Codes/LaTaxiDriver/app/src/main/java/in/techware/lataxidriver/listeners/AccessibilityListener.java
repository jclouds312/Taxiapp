package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.AccessibilityBean;

public interface AccessibilityListener {

    void onLoadCompleted(AccessibilityBean accessibilityBean);

    void onLoadFailed(String error);
}
