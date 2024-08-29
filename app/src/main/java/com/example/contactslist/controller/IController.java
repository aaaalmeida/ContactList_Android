package com.example.contactslist.controller;

import java.util.Date;
import java.util.List;

public interface IController<E> {
    List<E> getAll();
    Boolean add(E e);
    Boolean remove(Integer id);
    Boolean update(Integer id, String newName, Date newBirthday, String newEmail);
}
