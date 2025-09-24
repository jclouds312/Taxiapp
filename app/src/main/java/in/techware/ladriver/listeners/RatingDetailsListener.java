package in.techware.ladriver.listeners;

import in.techware.ladriver.model.RatingDetailsBean;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface RatingDetailsListener {
    void onLoadCompleted(RatingDetailsBean ratingDetailsBean);

    void onLoadFailed(String error);

}
