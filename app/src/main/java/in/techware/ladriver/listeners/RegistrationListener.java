package in.techware.ladriver.listeners;

import in.techware.ladriver.model.AuthBean;

/**
 * Created by Jemsheer K D on 24 April, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface RegistrationListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
