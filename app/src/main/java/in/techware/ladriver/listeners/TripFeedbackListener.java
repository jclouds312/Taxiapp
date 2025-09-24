package in.techware.ladriver.listeners;


import in.techware.ladriver.model.TripFeedbackBean;

public interface TripFeedbackListener {

    void onLoadFailed(String error);

    void onLoadCompleted(TripFeedbackBean tripFeedbackBean);

}
