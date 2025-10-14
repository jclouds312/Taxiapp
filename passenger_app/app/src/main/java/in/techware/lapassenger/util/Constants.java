package in.techware.lapassenger.util;

public class Constants {
    
    // SharedPreferences
    public static final String PREF_NAME = "SimplePassengerPrefs";
    
    // User data keys
    public static final String IS_LOGGED_IN = "is_logged_in";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_PHOTO = "user_photo";
    public static final String AUTH_TOKEN = "auth_token";
    public static final String FCM_TOKEN = "fcm_token";
    
    // Ride status
    public static final String RIDE_STATUS_REQUESTED = "requested";
    public static final String RIDE_STATUS_ACCEPTED = "accepted";
    public static final String RIDE_STATUS_ARRIVED = "arrived";
    public static final String RIDE_STATUS_STARTED = "started";
    public static final String RIDE_STATUS_COMPLETED = "completed";
    public static final String RIDE_STATUS_CANCELLED = "cancelled";
    
    // Payment methods
    public static final String PAYMENT_CASH = "cash";
    public static final String PAYMENT_CARD = "card";
    public static final String PAYMENT_WALLET = "wallet";
    public static final String PAYMENT_UPI = "upi";
    
    // Map constants
    public static final int DEFAULT_ZOOM = 15;
    public static final int LOCATION_UPDATE_INTERVAL = 5000; // 5 seconds
    public static final int FASTEST_UPDATE_INTERVAL = 2000; // 2 seconds
    
    // Request codes
    public static final int REQUEST_LOCATION_PERMISSION = 100;
    public static final int REQUEST_CALL_PERMISSION = 101;
    public static final int REQUEST_CAMERA_PERMISSION = 102;
    public static final int REQUEST_STORAGE_PERMISSION = 103;
    public static final int REQUEST_NOTIFICATION_PERMISSION = 104;
    
    // Activity request codes
    public static final int REQUEST_SELECT_LOCATION = 200;
    public static final int REQUEST_PAYMENT_METHOD = 201;
    public static final int REQUEST_PROFILE_UPDATE = 202;
    
    // Notification channels
    public static final String CHANNEL_RIDE_UPDATES = "ride_updates";
    public static final String CHANNEL_PROMOTIONS = "promotions";
    public static final String CHANNEL_GENERAL = "general";
    
    // API response codes
    public static final int SUCCESS = 200;
    public static final int ERROR = 400;
    public static final int UNAUTHORIZED = 401;
    
    // Time formats
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";
    
    // Currency
    public static final String CURRENCY_SYMBOL = "$";
    
    // Other constants
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int OTP_LENGTH = 4;
    public static final int PHONE_NUMBER_LENGTH = 10;
}