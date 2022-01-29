package com.example.webstore;

public class User {
    public String Fname, Lname , email , password, phone;

    public User(){

    }


    public User(String Fname, String Lname, String email, String password, String phone){
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
