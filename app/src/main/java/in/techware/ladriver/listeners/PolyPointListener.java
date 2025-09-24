package in.techware.ladriver.listeners;

import in.techware.ladriver.model.PolyPointBean;

/**
 * Created by Jemsheer K D on 09 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface PolyPointListener {
    void onLoadCompleted(PolyPointBean polyPointBean);

    void onLoadFailed(String error);
}
