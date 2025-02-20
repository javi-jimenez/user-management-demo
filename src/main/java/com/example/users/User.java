package com.example.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String username;
    private String name;
    private String email;
    private String gender;
    private String pictureUrl;

    // Constructors, Getters, and Setters

    public User() {}

    public User(String username, String name, String email, String gender, String pictureUrl) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.pictureUrl = pictureUrl;
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
