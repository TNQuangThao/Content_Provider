package com.example.contentprovider;
public class SmsModel {
    private String date;
    private String name;
    private String body;
    public SmsModel(String date, String name, String body) {
        this.date = date;
        this.name = name;
        this.body = body;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}