package in.techware.ladriver.listeners;

import in.techware.ladriver.model.AppStatusBean;
import in.techware.ladriver.model.BasicBean;

/**
 * Created by Jemsheer K D on 14 June, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface AppStatusListener {
    void onLoadCompleted(AppStatusBean appStatusBean);

    void onLoadFailed(String error);
}
