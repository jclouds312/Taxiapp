package in.techware.ladriver.listeners;


import in.techware.ladriver.model.AuthBean;
import in.techware.ladriver.model.TripSummaryBean;

public interface TripSummaryListener {

    void onLoadCompleted(TripSummaryBean tripSummaryBean);

    void onLoadFailed(String error);
}
