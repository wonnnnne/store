package com.devtw.store.domain.login.model;

import com.devtw.store.domain.user.model.User;
import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Getter
@Setter
public class LoginResponse {
    private User user;
    private String token;
}
