package com.example.contactslist.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.contactslist.dbHelper.ContactDbHelper;
import com.example.contactslist.model.Contact;

import java.util.Date;
import java.util.List;

public class ContactController implements  IController<Contact> {

    private SQLiteDatabase db;
    private ContactDbHelper dbHelper;

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public Boolean add(Contact contact) {
        ContentValues values = new ContentValues();

        db = dbHelper.getWritableDatabase();
        values.put("name", contact.getName());
        values.put("birthday", contact.getBirthday().toString());
        values.put("email", contact.getEmail());

        long resp = db.insert("contact", null, values);

        return !(resp == -1);
    }

    @Override
    public Boolean remove(Integer id) {
        int resp = db.delete();
        return null;
    }

    @Override
    public Boolean update(Integer id, String newName, Date newBirthday, String newEmail) {
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("birthday", newBirthday.toString());
        values.put("email", newEmail);
        int resp = db.update("contact", values, "id = " + id, null);
        return resp>0;
    }
}
