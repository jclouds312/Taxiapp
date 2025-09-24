package in.techware.ladriver.listeners;


import in.techware.ladriver.model.BasicBean;

public interface BasicListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);
}
