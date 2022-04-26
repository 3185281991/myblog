package com.example.myitem.entity;

public class Review {
    public int rBid;
    public String cusName;
    public String cusEmail;
    public String reName;
    public String reEmail;
    public String reContent;
    public String reTime;
    public String comValue;
    public String comTime;

    public int getrBid() {
        return rBid;
    }

    public void setrBid(int rBid) {
        this.rBid = rBid;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public String getReEmail() {
        return reEmail;
    }

    public void setReEmail(String reEmail) {
        this.reEmail = reEmail;
    }

    public String getReContent() {
        return reContent;
    }

    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public String getReTime() {
        return reTime;
    }

    public void setReTime(String reTime) {
        this.reTime = reTime;
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
        return "Review{" +
                "rBid=" + rBid +
                ", cusName='" + cusName + '\'' +
                ", cusEmail='" + cusEmail + '\'' +
                ", reName='" + reName + '\'' +
                ", reEmail='" + reEmail + '\'' +
                ", reContent='" + reContent + '\'' +
                ", reTime='" + reTime + '\'' +
                ", comValue='" + comValue + '\'' +
                ", comTime='" + comTime + '\'' +
                '}';
    }
}
