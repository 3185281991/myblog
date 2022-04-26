package com.example.myitem.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Customer {
    public int comBid;
    public String cusName;
    public String email;
    public String comValue;
    private String comTime;


    public int getComBid() {
        return comBid;
    }

    public void setComBid(int comBid) {
        this.comBid = comBid;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComValue() {
        return comValue;
    }

    public void setComValue(String comValue) {
        this.comValue = comValue;
    }

    public String getComTime() {
        return comTime;
    }

    public void setComTime(String comTime) {
        this.comTime = comTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "comBid=" + comBid +
                ", cusName='" + cusName + '\'' +
                ", email='" + email + '\'' +
                ", comValue='" + comValue + '\'' +
                ", comTime=" + comTime +
                '}';
    }
}
