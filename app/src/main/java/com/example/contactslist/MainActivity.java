package com.example.contactslist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactslist.adapter.ContactAdapter;
import com.example.contactslist.controller.ContactController;
import com.example.contactslist.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactController contactController = new ContactController(getApplicationContext());
        List<Contact> contacts = contactController.getAllAsList();
        ContactAdapter adaptador = new ContactAdapter(contacts,getApplicationContext());
        ListView lv = (ListView) findViewById(R.id.listViewContact);
        lv.setAdapter(adaptador);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                TextView txtId = (TextView) view.findViewById(R.id.textViewId);
                TextView txtNome = (TextView) view.findViewById(R.id.textViewNome);
                int pId = Integer.parseInt(txtId.getText().toString());
                String pNome = txtNome.getText().toString();
                Toast.makeText(getApplicationContext(), pId + pNome,Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}