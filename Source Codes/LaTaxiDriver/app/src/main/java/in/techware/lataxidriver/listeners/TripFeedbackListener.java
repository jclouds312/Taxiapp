package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.TripFeedbackBean;

public interface TripFeedbackListener {

    void onLoadFailed(String error);

    void onLoadCompleted(TripFeedbackBean tripFeedbackBean);

}
