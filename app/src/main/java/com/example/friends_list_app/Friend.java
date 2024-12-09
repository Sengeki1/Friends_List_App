package com.example.friends_list_app;

import java.time.LocalDate;

public class Friend {
    private String name;
    private String email;
    private String birthday;

    public Friend(String name, String email, String birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getBirthday() {
        return this.birthday;
    }
}
