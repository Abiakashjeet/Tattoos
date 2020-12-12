package com.example.tattoos.models;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("body")
    private String Fname;
    private String Lname;
    private String Email;
    private String Password;

    public Post(String fname, String lname, String email, String password) {
        Fname = fname;
        Lname = lname;
        Email = email;
        Password = password;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
