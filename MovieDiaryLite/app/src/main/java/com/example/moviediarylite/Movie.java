package com.example.moviediarylite;

public class Movie {
    String title;
    int year;
    String genre;
    int rating;
    String isFavourite;


    public Movie() {
    }

    public Movie(String title, int year, String genre, int rating, String isFavourite) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.isFavourite = isFavourite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFavourite() {
        return isFavourite;
    }

    public void setFavourite(String favourite) {
        isFavourite = favourite;
    }
}
