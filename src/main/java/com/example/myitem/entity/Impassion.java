package com.example.myitem.entity;

public class Impassion {
    public  int id;
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
        return "Impassion{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
