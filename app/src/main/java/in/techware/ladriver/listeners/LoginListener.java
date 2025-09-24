package in.techware.ladriver.listeners;

import in.techware.ladriver.model.AuthBean;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface LoginListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);

}
