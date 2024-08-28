package com.example.contactslist.controller;

import com.example.contactslist.model.Contact;

import java.util.List;

public class ContactController implements  IController<Contact> {
    private List<Contact> contactList;

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public Boolean add(Contact contact) {
        return null;
    }

    @Override
    public Boolean remove(Contact contact) {
        return null;
    }
}
