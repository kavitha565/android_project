package com.example.exchangelibrary;

public class PostFeed {
    String username, title, author, summary, genre, review, rating, location;

    public PostFeed(String username, String title, String author, String summary, String genre, String review, String rating, String location) {
        this.username = username;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.genre = genre;
        this.review = review;
        this.rating = rating;
        this.location = location;
    }

    public PostFeed() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
