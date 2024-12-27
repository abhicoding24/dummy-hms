package com.prac.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto{
    private String username;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;
}



