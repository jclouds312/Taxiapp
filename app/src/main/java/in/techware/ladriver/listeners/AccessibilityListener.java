package in.techware.ladriver.listeners;


import in.techware.ladriver.model.AccessibilityBean;

public interface AccessibilityListener {

    void onLoadCompleted(AccessibilityBean accessibilityBean);

    void onLoadFailed(String error);
}
