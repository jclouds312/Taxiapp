package in.techware.ladriver.model;

public class PayStatementBean {
    
    private String weekNumber;
    private String dateRange;
    private String totalEarnings;
    private String totalTrips;
    
    public PayStatementBean() {
    }
    
    public PayStatementBean(String weekNumber, String dateRange, String totalEarnings, String totalTrips) {
        this.weekNumber = weekNumber;
        this.dateRange = dateRange;
        this.totalEarnings = totalEarnings;
        this.totalTrips = totalTrips;
    }
    
    public String getWeekNumber() {
        return weekNumber;
    }
    
    public void setWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
    }
    
    public String getDateRange() {
        return dateRange;
    }
    
    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
    
    public String getTotalEarnings() {
        return totalEarnings;
    }
    
    public void setTotalEarnings(String totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
    
    public String getTotalTrips() {
        return totalTrips;
    }
    
    public void setTotalTrips(String totalTrips) {
        this.totalTrips = totalTrips;
    }
}