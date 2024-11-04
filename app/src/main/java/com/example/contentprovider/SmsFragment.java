package com.example.contentprovider;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class SmsFragment extends Fragment {
    SmsAdapter smsAdapter;
    private static final int SMS_PERMISSION_CODE = 101;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_sms);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
        } else {
//            List<SmsModel> smsList = getAllSms(requireContext());
//            smsAdapter = new SmsAdapter(smsList);
//            recyclerView.setAdapter(smsAdapter);
            loadSmsData();
        }
        return view;
    }
    public List<SmsModel> getAllSms(Context context) {
        List<SmsModel> smsList = new ArrayList<>();
        Uri smsUri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(smsUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                long dateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss   dd/MM/yyyy");
                String date = format.format(dateMillis);
                String contactName = getContactName(name, context);
                if (contactName != null) {
                    name = contactName; // Nếu tìm thấy tên liên hệ, thay thế số bằng tên
                }
                SmsModel sms = new SmsModel(date, name, body);
                smsList.add(sms);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return smsList;
    }
    public String getContactName(String phoneNumber, Context context) {
        String name = null;
        List<ContactModel> contactList = ContactFragment.getAllContacts(context);
        if (contactList != null) {
            for (ContactModel contact : contactList) {
                if (contact.getNumber().toString().trim().equals(phoneNumber)) {
                    name = contact.getName();
                    break;
                }
            }
        }
        return name;
    }
    public void loadSmsData() {
        List<SmsModel> smsList = getAllSms(requireContext());
        smsAdapter = new SmsAdapter(smsList);
        recyclerView.setAdapter(smsAdapter);
    }
}