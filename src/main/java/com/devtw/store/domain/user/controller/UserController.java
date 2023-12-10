package com.devtw.store.domain.user.controller;

import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "user/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/addUserForm";
        }
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        userService.join(user);
        return "redirect:/";
    }

}
