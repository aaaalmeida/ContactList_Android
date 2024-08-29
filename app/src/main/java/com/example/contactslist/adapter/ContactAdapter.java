package com.example.contactslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactslist.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<Contact> contactList;
    private LayoutInflater layoutInflater;

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.layoutInflater = LayoutInflater.from(context);
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_aluno, null);
        }
        Contact a = contactList.get(i);
        final TextView txtId = (TextView) view.findViewById(R.id.textViewId);
        final TextView txtNome = (TextView) view.findViewById(R.id.textViewNome);
        txtId.setText(String.valueOf(a.getId()));
        txtNome.setText(a.getName());
        return view;
    }
}
