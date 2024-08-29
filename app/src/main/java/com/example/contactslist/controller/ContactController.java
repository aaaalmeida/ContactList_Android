package com.example.contactslist.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.contactslist.dbHelper.ContactDbHelper;
import com.example.contactslist.model.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactController implements  IController<Contact> {
    private SQLiteDatabase db;
    private ContactDbHelper dbHelper;

    public ContactController(@Nullable Context context) {
        dbHelper = new ContactDbHelper(context);
    }

    @Override
    public Cursor find(Integer id) {
        return db.rawQuery("SELECT * FROM contact WHERE id = " + id, null, null);
    }

    @Override
    public Cursor getAll() {
        return db.rawQuery("SELECT * FROM contact", null, null);
    }

    @Override
    public List<Contact> getAllAsList() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = getAll();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = new Contact(
                    cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    new Date(cursor.getString(cursor.getColumnIndex("birthday"))),
                    cursor.getString(cursor.getColumnIndex("email"))
            );
            cursor.moveToNext();

            contacts.add(contact);
        }

        return contacts;
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
        int resp = db.delete("contact", "id = " + id, null);
        return resp > 0;
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
