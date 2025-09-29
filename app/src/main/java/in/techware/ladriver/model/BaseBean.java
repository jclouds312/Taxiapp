package in.techware.ladriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("message")
    @Expose
    private String webMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public String getErrorMsg() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorMsg(String error) {
        this.error = error;
    }

    public String getWebMessage() {
        return webMessage;
    }

    public void setWebMessage(String webMessage) {
        this.webMessage = webMessage;
    }
}
