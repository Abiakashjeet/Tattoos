package com.example.tattoos.models;

public class Comment {
    String Review;


    public Comment(String review) {
        this.Review = review;
    }

    public Comment(){}

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }
}
