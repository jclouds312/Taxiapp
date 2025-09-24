package in.techware.ladriver.listeners;

import in.techware.ladriver.model.HelpListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface HelpListListener {
    void onLoadCompleted(HelpListBean helpListBean);

    void onLoadFailed(String error);
}
