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
        holder.smsDate.setText(sms.getDate().toString());
        holder.smsName.setText(sms.getName());
        holder.smsBody.setText(sms.getBody());
    }
    @Override
    public int getItemCount() {
        return smsList.size();
    }
    static class SmsViewHolder extends RecyclerView.ViewHolder {
        TextView smsDate,smsName,smsBody;
        SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            smsDate = itemView.findViewById(R.id.smsDate);
            smsName = itemView.findViewById(R.id.smsName);
            smsBody = itemView.findViewById(R.id.smsBody);
        }
    }
}