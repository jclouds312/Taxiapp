package in.techware.lataxi.net.WSAsyncTasks;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FirebaseMessaging;

public class FCMRegistrationTask extends AsyncTask<String, Integer, String> {
    private FCMRegistrationTaskListener gcmRegistrationTaskListener;

    @Override
    protected String doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        String regID = null;
        try {
            regID = Tasks.await(FirebaseMessaging.getInstance().getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regID;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null)
            gcmRegistrationTaskListener.dataDownloadedSuccessfully(result);
        else
            gcmRegistrationTaskListener.dataDownloadFailed();
    }


    public interface FCMRegistrationTaskListener {
        void dataDownloadedSuccessfully(String result);

        void dataDownloadFailed();
    }

    public FCMRegistrationTaskListener getFCMRegistrationTaskListener() {
        return gcmRegistrationTaskListener;
    }

    public void setFCMRegistrationTaskListener(FCMRegistrationTaskListener gcmRegistrationTaskListener) {
        this.gcmRegistrationTaskListener = gcmRegistrationTaskListener;
    }
}
