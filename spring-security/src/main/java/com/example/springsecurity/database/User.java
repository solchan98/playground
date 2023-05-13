package com.example.springsecurity.database;

public class User {
    private final String username; // not user id, key of identifiable
    private final String userId;
    private final String password;
    private final String enabled;

    public User(String username, String userId, String password, String enabled) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEnabled() {
        return enabled;
    }
}
