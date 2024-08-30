package com.example.contactslist.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final Integer dbVersion = 1;
    public final String DB_TABLE = "contact";
    public final String DB_ID = "id";
    public final String DB_NAME = "name";
    public final String DB_BIRTHDAY = "birthday";
    public final String DB_EMAIL = "email";

    public ContactDbHelper(@Nullable Context context) {
        super(context, "contactsDB", null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_TABLE + "(" +
                DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DB_NAME + " TEXT," +
                DB_BIRTHDAY + " DATE," +
                DB_EMAIL + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  DB_TABLE);
        onCreate(sqLiteDatabase);
    }
}
