package com.example.contentprovider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder> {
    private List<CallLogModel> callLogModels;
    public CallLogAdapter(List<CallLogModel> callLogModels) {
        this.callLogModels = callLogModels;
    }
    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calllog, parent, false);
        return new CallLogViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        CallLogModel calllog = callLogModels.get(position);
        holder.callDate.setText(calllog.getDate().toString());
        holder.callName.setText(calllog.getNumber());
        holder.callType.setText(calllog.getType());
        holder.callDuration.setText(calllog.getDuration());
    }
    @Override
    public int getItemCount() {
        return callLogModels.size();
    }
    static class CallLogViewHolder extends RecyclerView.ViewHolder {
        TextView callDate,callName,callType,callDuration;

        CallLogViewHolder(@NonNull View itemView) {
            super(itemView);
            callDate = itemView.findViewById(R.id.callDate);
            callName = itemView.findViewById(R.id.callName);
            callType = itemView.findViewById(R.id.callType);
            callDuration = itemView.findViewById(R.id.callDuration);
        }
    }
}