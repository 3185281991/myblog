package com.example.myitem.entity;

public class Swiper {
    public int id;
    public String imgPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "swiper{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
