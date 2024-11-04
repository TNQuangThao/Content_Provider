package com.example.contentprovider;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
public class ContactFragment extends Fragment {
    ContactAdapter contactAdapter;
    public static final int CONTACT_PERMISSION_CODE = 103;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
        }else{
//            List<ContactModel> contactList = getAllContacts(requireContext());
//            contactAdapter = new ContactAdapter(contactList);
//            recyclerView.setAdapter(contactAdapter);
            loadContactData();
        }
        return view;
    }
    public static List<ContactModel> getAllContacts(Context context) {
        List<ContactModel> contactList = new ArrayList<>();
        Uri contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(contactsUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                ContactModel contact = new ContactModel(name, number);
                contactList.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contactList;
    }
    public void loadContactData() {
        List<ContactModel> contactList = getAllContacts(requireContext());
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);
    }
}