package in.techware.ladriver.listeners;

import in.techware.ladriver.model.TripListBean;

/**
 * Created by Jemsheer K D on 08 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface TripListListener {

    void onLoadCompleted(TripListBean tripListBean);

    void onLoadFailed(String error);

}
