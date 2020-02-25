package com.kozhukhar.carshop_online.db.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private Boolean news;

    private String name;

    private String surname;

    private List<String> roles;

    private Boolean ban;

    public User() {
    }

    public User(String username, String password, String email, Boolean news) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
