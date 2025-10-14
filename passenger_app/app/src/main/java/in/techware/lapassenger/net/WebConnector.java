package in.techware.lapassenger.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebConnector extends AsyncTask<Void, Void, String> {
    
    private static final String TAG = "WebConnector";
    private static final int TIMEOUT = 30; // seconds
    
    private Context context;
    private String url;
    private HashMap<String, String> params;
    private WebConnectorListener listener;
    private boolean isError = false;
    private String errorMessage = "";
    
    public interface WebConnectorListener {
        void onSuccess(String response);
        void onError(String error);
    }
    
    public WebConnector(Context context, String url, HashMap<String, String> params, WebConnectorListener listener) {
        this.context = context;
        this.url = url;
        this.params = params;
        this.listener = listener;
    }
    
    @Override
    protected String doInBackground(Void... voids) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
            
            FormBody.Builder formBuilder = new FormBody.Builder();
            
            // Add parameters
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            
            RequestBody requestBody = formBuilder.build();
            
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            
            Log.d(TAG, "Request URL: " + url);
            Log.d(TAG, "Request Params: " + params.toString());
            
            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "Response: " + responseBody);
                return responseBody;
            } else {
                isError = true;
                errorMessage = "Server error: " + response.code();
                return null;
            }
            
        } catch (IOException e) {
            isError = true;
            errorMessage = "Network error: " + e.getMessage();
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            isError = true;
            errorMessage = "Error: " + e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        
        if (listener != null) {
            if (!isError && response != null) {
                listener.onSuccess(response);
            } else {
                listener.onError(errorMessage);
            }
        }
    }
}