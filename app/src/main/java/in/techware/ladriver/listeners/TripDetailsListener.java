package in.techware.lataxidriver.listeners;

import in.techware.lataxidriver.model.TripBean;

/**
 * Created by Jemsheer K D on 09 June, 2017.
 * Package in.techware.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface TripDetailsListener {

    void onLoadCompleted(TripBean tripBean);

    void onLoadFailed(String error);
}
