package com.example.tattoos.models;

public class User {

    String FirstName;
    String LastName;
    String Email;
    String Pass;


    public User(String firstName, String lastName, String email, String pass) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Pass=pass;

    }

    public User(){}

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }


}