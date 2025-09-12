package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.BasicBean;

public interface BasicListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);
}
