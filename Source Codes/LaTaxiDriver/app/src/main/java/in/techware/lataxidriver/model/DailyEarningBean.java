package in.techware.lataxidriver.model;

/**
 * Created by Jemsheer K D on 16 May, 2017.
 * Package in.techware.lataxidriver.model
 * Project LaTaxiDriver
 */

public class DailyEarningBean extends BaseBean {

    private int day;
    private String amountPayable;
    private float amount;
    private long hoursOnline;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        this.amountPayable = amountPayable;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getHoursOnline() {
        return hoursOnline;
    }

    public void setHoursOnline(long hoursOnline) {
        this.hoursOnline = hoursOnline;
    }
}
