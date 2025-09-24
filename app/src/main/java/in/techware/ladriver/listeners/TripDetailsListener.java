package in.techware.ladriver.listeners;

import in.techware.ladriver.model.TripBean;

/**
 * Created by Jemsheer K D on 09 June, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface TripDetailsListener {

    void onLoadCompleted(TripBean tripBean);

    void onLoadFailed(String error);
}
