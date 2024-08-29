package com.example.contactslist.model;

import androidx.annotation.Nullable;

import java.util.Date;

public class Contact {
    private Long id;
    private String name;
    private Date birthday;
    private String email;

    public Contact(@Nullable Long id, String name, Date birthday, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                "name=" + name +
                ", birthday=" + birthday +
                ", email=" + email +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
