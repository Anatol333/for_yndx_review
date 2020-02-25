package com.kozhukhar.carshop_online.db.bean;

import com.kozhukhar.carshop_online.db.dto.User;
import lombok.Data;

@Data
public class RegUserBean {

    private String username;

    private String password;

    private String passwordRep;

    private String email;

    private String captchaCode;

    private String hiddenCaptcha;

    private Boolean news;

    private String name;

    private String surname;

    public User getUserEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setNews(news);
        user.setName(name);
        user.setSurname(surname);
        return user;
    }
}
