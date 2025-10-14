package in.techware.lapassenger.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.techware.lapassenger.R;
import in.techware.lapassenger.activity.HomeActivity;
import in.techware.lapassenger.util.Constants;

public class PassengerFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMService";
    
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        
        // Check if message contains data payload
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            handleDataMessage(remoteMessage.getData());
        }
        
        // Check if message contains notification payload
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getTitle(), 
                           remoteMessage.getNotification().getBody());
        }
    }
    
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);
        
        // Save token to SharedPreferences
        getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE)
                .edit()
                .putString(Constants.FCM_TOKEN, token)
                .apply();
        
        // Send token to server if user is logged in
        sendTokenToServer(token);
    }
    
    private void handleDataMessage(java.util.Map<String, String> data) {
        String type = data.get("type");
        String title = data.get("title");
        String message = data.get("message");
        
        if (type != null) {
            switch (type) {
                case "ride_accepted":
                    sendNotification("Ride Accepted", "Your ride has been accepted by a driver");
                    break;
                case "driver_arrived":
                    sendNotification("Driver Arrived", "Your driver has arrived at pickup location");
                    break;
                case "ride_started":
                    sendNotification("Ride Started", "Your ride has started");
                    break;
                case "ride_completed":
                    sendNotification("Ride Completed", "Your ride has been completed");
                    break;
                default:
                    sendNotification(title != null ? title : "Simple Taxi", 
                                   message != null ? message : "You have a new notification");
                    break;
            }
        }
    }
    
    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        
        String channelId = Constants.CHANNEL_RIDE_UPDATES;
        
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent);
        
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Ride Updates",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Notifications for ride updates");
            notificationManager.createNotificationChannel(channel);
        }
        
        notificationManager.notify(0, notificationBuilder.build());
    }
    
    private void sendTokenToServer(String token) {
        // TODO: Implement API call to update FCM token on server
    }
}