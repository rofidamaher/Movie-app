package com.example.mymovieapp;

public class Movie {
    String title ,description,imgurl;

    public Movie(){}

    public Movie(String title, String description, String imgurl) {
        this.title = title;
        this.description = description;
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
