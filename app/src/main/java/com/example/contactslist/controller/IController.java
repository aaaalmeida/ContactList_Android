package com.example.contactslist.controller;

import android.database.Cursor;

import java.util.Date;
import java.util.List;

public interface IController<E> {
    Cursor find(Integer id);
    Cursor getAll();
    List<E> getAllAsList();
    Boolean add(E e);
    Boolean remove(Integer id);
    Boolean update(Integer id, String newName, Date newBirthday, String newEmail);
}
