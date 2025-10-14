package in.techware.ladriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.techware.ladriver.R;
import in.techware.ladriver.model.PayStatementBean;

public class PayStatementsAdapter extends RecyclerView.Adapter<PayStatementsAdapter.ViewHolder> {

    private Context context;
    private List<PayStatementBean> payStatementsList;
    
    public PayStatementsAdapter(Context context, List<PayStatementBean> payStatementsList) {
        this.context = context;
        this.payStatementsList = payStatementsList;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pay_statement, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PayStatementBean statement = payStatementsList.get(position);
        
        holder.tvWeek.setText(statement.getWeekNumber());
        holder.tvDateRange.setText(statement.getDateRange());
        holder.tvEarnings.setText(statement.getTotalEarnings());
        holder.tvTrips.setText(statement.getTotalTrips() + " trips");
    }
    
    @Override
    public int getItemCount() {
        return payStatementsList != null ? payStatementsList.size() : 0;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeek;
        TextView tvDateRange;
        TextView tvEarnings;
        TextView tvTrips;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeek = itemView.findViewById(R.id.tv_week);
            tvDateRange = itemView.findViewById(R.id.tv_date_range);
            tvEarnings = itemView.findViewById(R.id.tv_earnings);
            tvTrips = itemView.findViewById(R.id.tv_trips);
        }
    }
}