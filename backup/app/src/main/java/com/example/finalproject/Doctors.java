package com.example.finalproject;

public class Doctors {
    private String name;
    private String email;
    private String specialization;
    private String bio;
    private int photo;
    private int docphonenum;

    public Doctors(String name, String email, String specialization, String bio, int photo, int docphonenum) {
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.bio = bio;
        this.photo = photo;
        this.docphonenum = docphonenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getDocphonenum() {
        return docphonenum;
    }

    public void setDocphonenum(int docphonenum) {
        this.docphonenum = docphonenum;
    }
}
