package com.example.myitem.entity;

public class Lauds {
    public int bid;
    public String email;
    public int isLaud;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsLaud() {
        return isLaud;
    }

    public void setIsLaud(int isLaud) {
        this.isLaud = isLaud;
    }

    @Override
    public String toString() {
        return "Lauds{" +
                "bid=" + bid +
                ", email='" + email + '\'' +
                ", isLaud=" + isLaud +
                '}';
    }
}
