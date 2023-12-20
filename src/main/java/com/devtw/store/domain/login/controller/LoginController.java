package com.devtw.store.domain.login.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.login.model.LoginResponse;
import com.devtw.store.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ApiResponse<LoginResponse> loginUser(@RequestBody Map<String,String> param) {

        return loginService.login(param);
    }
}
