package com.example.fernanda.typicodeapp.model;

/**
 * Created by Fernanda on 12/11/2016.
 * POJO class (plain old java obj.)
 */

public class User {
    String name, username;
    String email, phone, website;
    int id;

    public User(String name, String username, String email, String phone, String website, int id) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public int getId() {
        return id;
    }
}
