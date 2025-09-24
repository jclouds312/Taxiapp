package in.techware.ladriver.listeners;

import in.techware.ladriver.model.WeeklyEarningsBean;

/**
 * Created by Jemsheer K D on 16 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface WeeklyEarningsListener {

    void onLoadCompleted(WeeklyEarningsBean weeklyEarningsBean);

    void onLoadFailed(String error);
}
