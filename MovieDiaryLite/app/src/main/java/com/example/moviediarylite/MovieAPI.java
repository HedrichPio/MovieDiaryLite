package com.example.moviediarylite;

import android.graphics.Bitmap;

public class MovieAPI {

    String id;
    String title;
    String year;
    String rating;
    Bitmap imgUrl;

    public MovieAPI() {
    }

    public MovieAPI(String id, String title, String year, String rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public MovieAPI(String id, String title, String year, String rating, Bitmap imgUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imgUrl = imgUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Bitmap imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
