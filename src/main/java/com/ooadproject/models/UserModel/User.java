package com.ooadproject.models.UserModel;

public class User {
    protected String name;
    protected String username;
    protected String password;
    protected String role;

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return role;
    }
}