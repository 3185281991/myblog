package com.example.myitem.entity;

public class Advertise {
    public int id;
    public String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Advertise{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
