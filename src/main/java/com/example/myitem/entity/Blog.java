package com.example.myitem.entity;


public class Blog {
    public int bid;
    public String author;
    public String label;
    public String type;
    public String title;
    public String content;
    public String img;
    private String time;
    public int lauds;
    public int views;
    public int commentsNum;
    public String top;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLauds() {
        return lauds;
    }

    public void setLauds(int lauds) {
        this.lauds = lauds;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", author='" + author + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", time=" + time +
                ", lauds=" + lauds +
                ", views='" + views + '\'' +
                ", commentsNum='" + commentsNum + '\'' +
                ", top='" + top + '\'' +
                '}';
    }
}
