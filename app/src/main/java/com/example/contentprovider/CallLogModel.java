package com.example.contentprovider;
public class CallLogModel {
    private String date;
    private String number;
    private String type;
    private String duration;
    public CallLogModel(String date, String number, String type, String duration) {
        this.date = date;
        this.number = number;
        this.type = type;
        this.duration = duration;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
