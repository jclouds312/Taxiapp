package in.techware.lataxi.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.techware.lataxi.activity.DriverRatingActivity;
import in.techware.lataxi.app.App;
import in.techware.lataxi.model.BasicBean;
import in.techware.lataxi.model.SuccessBean;
import in.techware.lataxi.net.parsers.TripEndParser;
import in.techware.lataxi.util.AppConstants;

public class LaTaxiFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "LFMService";
    private SuccessBean successBean;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        SharedPreferences preferences = getSharedPreferences(AppConstants.PREFERENCE_NAME_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppConstants.PREFERENCE_KEY_SESSION_GCM_ID, token);
        editor.apply();

        // TODO: Implement your own logic to submit the token to your backend server
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

//        Log.i(TAG, "onMessageReceived: Remote Message : " + remoteMessage.);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.i(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
            Log.i(TAG, "Response: " + remoteMessage.getData().get("response"));


            String body = remoteMessage.getData().get("response");
            TripEndParser tripEndParser = new TripEndParser();
            BasicBean basicBean = tripEndParser.parseBasicResponse(body);

            if (basicBean == null)
                stopSelf();
            else {
                if (basicBean.getStatus().equalsIgnoreCase("Success")) {
//                    initiateDriverRatingService(basicBean.getId());
                    initiateDriverRatingService(basicBean.getId());
                } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                    stopSelf();
                } else {
                    stopSelf();
                }
            }

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String body = remoteMessage.getNotification().getBody();

            TripEndParser tripEndParser = new TripEndParser();
            BasicBean basicBean = tripEndParser.parseBasicResponse(body);

            if (basicBean == null)
                stopSelf();
            else {
                if (basicBean.getStatus().equalsIgnoreCase("Success")) {
//                    initiateDriverRatingService(basicBean.getId());
                    initiateDriverRatingService(basicBean.getId());
                } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                    stopSelf();
                } else {
                    stopSelf();
                }
            }


        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    public void initiateDriverRatingService(String id) {

        Log.i(TAG, "initiateDriverRatingService: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SERVICE STARTED>>>>>>>>>>>>>>>>>>>>>");

        startActivity(new Intent(this, DriverRatingActivity.class)
                .putExtra("id", id)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

    }
}
