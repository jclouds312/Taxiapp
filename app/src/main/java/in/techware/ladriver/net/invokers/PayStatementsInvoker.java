package in.techware.ladriver.net.invokers;

import android.content.Context;

import java.util.List;

import in.techware.ladriver.model.PayStatementBean;

public class PayStatementsInvoker {
    
    private Context context;
    private OnPayStatementsLoadListener listener;
    
    public interface OnPayStatementsLoadListener {
        void onSuccess(List<PayStatementBean> statements, double totalEarnings, int totalTrips);
        void onError(String error);
    }
    
    public PayStatementsInvoker(Context context, OnPayStatementsLoadListener listener) {
        this.context = context;
        this.listener = listener;
    }
    
    public void loadPayStatements() {
        // TODO: Implement API call to load pay statements
        // For now, just call the error callback
        if (listener != null) {
            listener.onError("Not implemented yet");
        }
    }
}