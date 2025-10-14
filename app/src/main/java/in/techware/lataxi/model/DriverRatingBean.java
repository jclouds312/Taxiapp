package in.techware.lataxi.model;


import java.util.ArrayList;

public class DriverRatingBean extends BaseBean {

    private String rating;
    private ArrayList<String> badFeedbackList = new ArrayList<>();
    private ArrayList<String> goodFeedbackList = new ArrayList<>();
    private String feedback;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<String> getBadFeedbackList() {
        return badFeedbackList;
    }

    public void setBadFeedbackList(ArrayList<String> badFeedbackList) {
        this.badFeedbackList = badFeedbackList;
    }

    public ArrayList<String> getGoodFeedbackList() {
        return goodFeedbackList;
    }

    public void setGoodFeedbackList(ArrayList<String> goodFeedbackList) {
        this.goodFeedbackList = goodFeedbackList;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
