package com.devtw.store.domain.login.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.common.JwtProvider;
import com.devtw.store.domain.login.model.LoginResponse;
import com.devtw.store.domain.login.service.LoginService;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.login.model.LoginForm;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    private final JwtProvider jwtProvider;


    @PostMapping("/login")
    public ApiResponse<LoginResponse> loginUser(@RequestBody Map<String,String> param) {

        String email = param.get("email");
        String password = param.get("password");

        if (!loginService.isJoinedUser(email)) {
            return ApiResponse.fail("회원이 아닙니다. 회원가입을 먼저 진행해주세요.");
        }

        User loginUser = loginService.login(email, password);

        if (loginUser == null) {
            return  ApiResponse.fail("비밀번호가 일치하지 않습니다. 비밀번호를 확인 후 다시 진행해주세요.");
        }
        // 로그인 성공 시 JWT 토큰 생성
        String jwtToken = jwtProvider.createToken(loginUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(loginUser);
        loginResponse.setToken(jwtToken);


        // JWT 토큰을 HTTP 응답 헤더에 실어서 전달
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);

        return ApiResponse.success(loginResponse);
    }
}
