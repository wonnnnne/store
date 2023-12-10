package com.devtw.store.domain.user.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<User> registerUser(@RequestBody User user) {

        User searchUser = userService.getUserByEmail(user.getEmail());

        if (searchUser == null) {
            User joinedUser = userService.saveUser(user);
            return ApiResponse.success(joinedUser);
        } else {
            return ApiResponse.fail("이미 가입된 정보입니다.");
        }
    }
}
