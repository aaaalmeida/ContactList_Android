package com.example.contactslist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactslist.adapter.ContactAdapter;
import com.example.contactslist.controller.ContactController;
import com.example.contactslist.model.Contact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {
    private ContactController contactController;
    private List<Contact> contactList;
    private ContactAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactController = new ContactController(getApplicationContext());
        contactList = contactController.getAllAsList();

        adaptador = new ContactAdapter(contactList, getApplicationContext());

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
                Toast.makeText(getApplicationContext(), pNome,Toast.LENGTH_LONG).show();
                return true;
            }
        });

        loadContactsFromDatabase();

        Button bAddContact = (Button) findViewById(R.id.addContactButton);
        bAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
                adaptador.notifyDataSetChanged();
            }
        });
    }

    private void loadContactsFromDatabase() {
        List<Contact> contactsFromDb = contactController.getAllAsList();

        if (contactsFromDb != null && !contactsFromDb.isEmpty()) {
            contactList.clear();
            contactList.addAll(contactsFromDb);
            adaptador.notifyDataSetChanged();
        }
    }

    protected void addNewContact() {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.add_contact_modal, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final String name = promptView.findViewById(R.id.nameNewContact).toString();
        final String birthday = promptView.findViewById(R.id.birthdayNewContact).toString();
        final String email = promptView.findViewById(R.id.emailNewContact).toString();
        final Date date = stringToDate(birthday);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        contactController.add(new Contact(
                                null,
                                name,
                                date,
                                email
                        ));
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private Date stringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = "16/10/2015";
        try {
            Date res = formatter.parse(data);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}