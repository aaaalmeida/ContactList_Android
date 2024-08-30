package com.example.contactslist.controller;

import android.annotation.SuppressLint;
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
    private final ContactDbHelper dbHelper;

    public ContactController(@Nullable Context context) {
        dbHelper = new ContactDbHelper(context);
    }

    @Override
    public Cursor find(Integer id) {
        return db.rawQuery("SELECT * FROM " + dbHelper.DB_TABLE + " WHERE id = " + id, null, null);
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
            @SuppressLint("Range") Contact contact = new Contact(
                    cursor.getLong(cursor.getColumnIndexOrThrow(dbHelper.DB_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DB_NAME)),
                    new Date(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DB_BIRTHDAY))),
                    cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.DB_EMAIL))
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
        values.put(dbHelper.DB_NAME, contact.getName());
        values.put(dbHelper.DB_BIRTHDAY, contact.getBirthday().toString());
        values.put(dbHelper.DB_EMAIL, contact.getEmail());

        long resp = db.insert(dbHelper.DB_TABLE, null, values);

        return !(resp == -1);
    }

    @Override
    public Boolean remove(Integer id) {
        int resp = db.delete(dbHelper.DB_TABLE, "id = " + id, null);
        return resp > 0;
    }

    @Override
    public Boolean update(Integer id, String newName, Date newBirthday, String newEmail) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.DB_NAME, newName);
        values.put(dbHelper.DB_BIRTHDAY, newBirthday.toString());
        values.put(dbHelper.DB_EMAIL, newEmail);
        int resp = db.update(dbHelper.DB_TABLE, values, "id = " + id, null);
        return resp>0;
    }
}
