package com.example.contactslist.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final Integer dbVersion = 1;
    public ContactDbHelper(@Nullable Context context) {
        super(context, "contactsDB", null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querry = "CREATE TABLE contact (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome STRING," +
                "birthday DATE," +
                "email STRING" +
                ");";

        sqLiteDatabase.execSQL(querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(sqLiteDatabase);
    }
}
