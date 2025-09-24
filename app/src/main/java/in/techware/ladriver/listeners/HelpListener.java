package in.techware.ladriver.listeners;

import in.techware.ladriver.model.HelpBean;

/**
 * Created by Jemsheer K D on 20 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface HelpListener {
    void onLoadCompleted(HelpBean helpBean);

    void onLoadFailed(String error);
}
