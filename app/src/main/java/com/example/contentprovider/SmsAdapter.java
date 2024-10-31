package com.example.contentprovider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsViewHolder> {
    private List<SmsModel> smsList;
    public SmsAdapter(List<SmsModel> smsList) {
        this.smsList = smsList;
    }
    @NonNull
    @Override
    public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms, parent, false);
        return new SmsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        SmsModel sms = smsList.get(position);
        holder.addressTextView.setText(sms.getAddress());
        holder.bodyTextView.setText(sms.getBody());
    }
    @Override
    public int getItemCount() {return smsList.size();}
    public static class SmsViewHolder extends RecyclerView.ViewHolder {
        TextView addressTextView, bodyTextView;
        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
            bodyTextView = itemView.findViewById(R.id.textViewBody);
        }
    }
}
