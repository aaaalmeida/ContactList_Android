package com.example.contactslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactslist.R;
import com.example.contactslist.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private final List<Contact> contactList;
    private final Context context;

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contactList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        }
        Contact c = (Contact) getItem(i);
        final TextView idTV = (TextView) view.findViewById(R.id.textViewId);
        idTV.setText(String.valueOf(c.getId()));

        final TextView nomeTV = (TextView) view.findViewById(R.id.textViewNome);
        nomeTV.setText(c.getName());

        final TextView birthdayTV = (TextView) view.findViewById(R.id.textViewBirthday);
        birthdayTV.setText(c.getBirthday().toString());

        final TextView emailTV = (TextView) view.findViewById(R.id.textViewEmail);
        emailTV.setText(c.getEmail());

        return view;
    }
}
