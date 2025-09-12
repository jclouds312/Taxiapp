package in.techware.lataxidriver.listeners;

import in.techware.lataxidriver.model.AppStatusBean;
import in.techware.lataxidriver.model.BasicBean;

/**
 * Created by Jemsheer K D on 14 June, 2017.
 * Package in.techware.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface AppStatusListener {
    void onLoadCompleted(AppStatusBean appStatusBean);

    void onLoadFailed(String error);
}
