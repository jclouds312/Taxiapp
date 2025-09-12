package in.techware.lataxidriver.listeners;


import in.techware.lataxidriver.model.AuthBean;
import in.techware.lataxidriver.model.TripSummaryBean;

public interface TripSummaryListener {

    void onLoadCompleted(TripSummaryBean tripSummaryBean);

    void onLoadFailed(String error);
}
