package com.example.contentprovider;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class CallLogFragment extends Fragment {
    CallLogAdapter callLogAdapter;
    private static final int CALL_LOG_PERMISSION_CODE = 102;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_log, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_callog);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_CALL_LOG}, CALL_LOG_PERMISSION_CODE);
        } else {
//            List<CallLogModel> callLogList = getAllCallLogs(requireContext());
//            callLogAdapter = new CallLogAdapter(callLogList);
//            recyclerView.setAdapter(callLogAdapter);
            loadCallLogData();
        }
        return view;
    }
    public List<CallLogModel> getAllCallLogs(Context context) {
        List<CallLogModel> callLogList = new ArrayList<>();
        Uri callLogUri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(callLogUri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                long dateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE));
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss   dd/MM/yyyy");
                String date = format.format(dateMillis);
                String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                int t = cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                String type=getCallTypeString(t);
                int d =cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));
                String duration= formatDuration(d);
                String contactName = getContactName(number, context);
                if (contactName != null) {
                    number = contactName; // Nếu tìm thấy tên liên hệ, thay thế số bằng tên
                }
                CallLogModel callLog = new CallLogModel(date, number, type,duration);
                callLogList.add(callLog);
            }
            cursor.close();
        } else {
            Log.d("CallLog", "Cursor is null, unable to retrieve call logs.");
        }
        return callLogList;
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
    public String getCallTypeString(int type) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                return "Incoming";
            case CallLog.Calls.OUTGOING_TYPE:
                return "Outgoing";
            case CallLog.Calls.MISSED_TYPE:
                return "Missed";
            default:
                return "Unknown"; // Trường hợp không xác định
        }
    }public String formatDuration(int durationInSeconds) {
        int hours = durationInSeconds / 3600;
        int minutes = (durationInSeconds % 3600) / 60;
        int seconds = durationInSeconds % 60;

        // Định dạng kết quả dưới dạng chuỗi HH:mm:ss
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void loadCallLogData() {
        List<CallLogModel> callLogList = getAllCallLogs(requireContext());
        callLogAdapter = new CallLogAdapter(callLogList);
        recyclerView.setAdapter(callLogAdapter);
    }
}