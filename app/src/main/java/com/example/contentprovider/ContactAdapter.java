package com.example.contentprovider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<ContactModel> contactModels;
    public ContactAdapter(List<ContactModel> contactModels) {
        this.contactModels = contactModels;
    }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contactModel = contactModels.get(position);
        holder.contactName.setText(contactModel.getName());
        holder.contactNumber.setText(contactModel.getNumber());
    }
    @Override
    public int getItemCount() {
        return contactModels.size();
    }
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactName,contactNumber;
        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactName);
            contactNumber = itemView.findViewById(R.id.contactNumber);
        }
    }
}