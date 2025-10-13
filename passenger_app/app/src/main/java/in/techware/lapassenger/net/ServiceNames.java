package in.techware.lapassenger.net;

public class ServiceNames {
    
    // Base URL - Same backend as driver app
    private static final String PRODUCTION_API = "http://techlabz.in";
    
    // API Version for passenger
    public static final String API_VERSION = "/lapassenger/Webservices_passenger";
    
    // Upload paths
    public static final String PATH_UPLOADS = "/lapassenger/";
    
    // API URL
    private static final String API = PRODUCTION_API + API_VERSION;
    
    // Image upload URL
    public static final String API_UPLOADS = PRODUCTION_API + PATH_UPLOADS;
    
    // Google Maps API
    public static final String POLY_POINTS = "https://maps.googleapis.com/maps/api/directions/json?";
    public static final String GEOCODE_API = "https://maps.googleapis.com/maps/api/geocode/json?";
    public static final String PLACES_API = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
    
    // Authentication endpoints
    public static final String REGISTRATION = API + "/registration?";
    public static final String LOGIN = API + "/login?";
    public static final String FORGOT_PASSWORD = API + "/forgot_password?";
    public static final String VERIFY_OTP = API + "/verify_otp?";
    public static final String RESEND_OTP = API + "/resend_otp?";
    public static final String LOGOUT = API + "/logout?";
    
    // Profile endpoints
    public static final String GET_PROFILE = API + "/get_profile?";
    public static final String UPDATE_PROFILE = API + "/update_profile?";
    public static final String UPLOAD_PROFILE_PHOTO = API + "/profile_photo_upload?";
    
    // Ride booking endpoints
    public static final String REQUEST_RIDE = API + "/request_ride?";
    public static final String CANCEL_RIDE = API + "/cancel_ride?";
    public static final String GET_RIDE_STATUS = API + "/get_ride_status?";
    public static final String TRACK_RIDE = API + "/track_ride?";
    public static final String GET_NEARBY_DRIVERS = API + "/get_nearby_drivers?";
    public static final String ESTIMATE_FARE = API + "/estimate_fare?";
    
    // Trip history endpoints
    public static final String TRIP_HISTORY = API + "/trip_history?";
    public static final String TRIP_DETAILS = API + "/trip_details?";
    public static final String TRIP_INVOICE = API + "/trip_invoice?";
    
    // Rating and feedback
    public static final String RATE_DRIVER = API + "/rate_driver?";
    public static final String SUBMIT_FEEDBACK = API + "/submit_feedback?";
    public static final String GET_DRIVER_DETAILS = API + "/get_driver_details?";
    
    // Payment endpoints
    public static final String GET_PAYMENT_METHODS = API + "/get_payment_methods?";
    public static final String ADD_PAYMENT_METHOD = API + "/add_payment_method?";
    public static final String DELETE_PAYMENT_METHOD = API + "/delete_payment_method?";
    public static final String SET_DEFAULT_PAYMENT = API + "/set_default_payment?";
    public static final String PROCESS_PAYMENT = API + "/process_payment?";
    public static final String GET_WALLET_BALANCE = API + "/get_wallet_balance?";
    public static final String ADD_WALLET_MONEY = API + "/add_wallet_money?";
    
    // Notifications
    public static final String UPDATE_FCM_TOKEN = API + "/update_fcm_token?";
    public static final String GET_NOTIFICATIONS = API + "/get_notifications?";
    
    // Support
    public static final String HELP_LIST = API + "/help_list?";
    public static final String SUBMIT_ISSUE = API + "/submit_issue?";
    public static final String GET_FAQ = API + "/get_faq?";
    
    // App status
    public static final String APP_STATUS = API + "/app_status?";
    public static final String GET_VEHICLE_TYPES = API + "/get_vehicle_types?";
    public static final String GET_PROMO_CODES = API + "/get_promo_codes?";
    public static final String APPLY_PROMO_CODE = API + "/apply_promo_code?";
}