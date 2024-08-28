package com.example.contactslist.controller;

import java.util.List;

public interface IController<E> {
    List<E> getAll();
    Boolean add(E e);
    Boolean remove(E e);
}
