package in.techware.lataxidriver.listeners;

import in.techware.lataxidriver.model.AuthBean;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package in.techware.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface LoginListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);

}
