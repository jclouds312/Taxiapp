package in.techware.ladriver.net.WSAsyncTasks;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class FCMRegistrationHandler {
    private FCMRegistrationTaskListener fcmRegistrationTaskListener;

    public void performFCMRegistration() {
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result != null) {
                            fcmRegistrationTaskListener.dataDownloadedSuccessfully(result);
                        } else {
                            fcmRegistrationTaskListener.dataDownloadFailed();
                        }
                    }
                });
    }

    public interface FCMRegistrationTaskListener {
        void dataDownloadedSuccessfully(String result);

        void dataDownloadFailed();
    }

    public FCMRegistrationTaskListener getFCMRegistrationTaskListener() {
        return fcmRegistrationTaskListener;
    }

    public void setFCMRegistrationTaskListener(FCMRegistrationTaskListener fcmRegistrationTaskListener) {
        this.fcmRegistrationTaskListener = fcmRegistrationTaskListener;
    }
}
