package com.devtw.store.domain.login.model;

import com.devtw.store.domain.user.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    private final User user;
    private final String token;
}
