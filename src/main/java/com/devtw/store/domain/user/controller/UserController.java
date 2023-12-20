package com.devtw.store.domain.user.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
