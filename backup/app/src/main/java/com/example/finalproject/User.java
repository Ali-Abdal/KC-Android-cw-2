package com.example.finalproject;

public class User {

        private String fullname,email,address,age,Photo;


    public User(){

    }

    public User(String fullname,String email, String address, String age,String Photo ) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.age = age;
        this.Photo = Photo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
