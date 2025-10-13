package in.techware.lapassenger.app;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import timber.log.Timber;

public class PassengerApp extends Application {

    private static PassengerApp mInstance;
    
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        
        // Initialize Crashlytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        
        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
    
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    
    public static synchronized PassengerApp getInstance() {
        return mInstance;
    }
    
    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }
}