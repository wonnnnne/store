package com.devtw.store.domain.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginForm {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
