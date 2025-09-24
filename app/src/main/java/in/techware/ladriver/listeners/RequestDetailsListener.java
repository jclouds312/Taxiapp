package in.techware.ladriver.listeners;

import in.techware.ladriver.model.RequestDetailsBean;

/**
 * Created by Jemsheer K D on 08 June, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface RequestDetailsListener {

    void onLoadCompleted(RequestDetailsBean requestDetailsBean);

    void onLoadFailed(String error);
}
