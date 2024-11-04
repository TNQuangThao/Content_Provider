package com.example.contentprovider;

import java.util.Date;

public class ContactModel {
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public ContactModel(String name, String number) {
        this.name = name;
        this.number = number;
    }
}