package com.devtw.store.domain.login.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.login.service.LoginService;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.login.model.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ApiResponse<User> loginUser(@RequestBody Map<String,String> param) {

        String email = param.get("email");
        String password = param.get("password");

        if (!loginService.isJoinedUser(email)) {
            return ApiResponse.fail("회원이 아닙니다. 회원가입을 먼저 진행해주세요.");
        }

        User loginUser = loginService.login(email, password);

        if (loginUser == null) {
            return  ApiResponse.fail("비밀번호가 일치하지 않습니다. 비밀번호를 확인 후 다시 진행해주세요.");
        }

        return ApiResponse.success(loginUser);
    }
}
