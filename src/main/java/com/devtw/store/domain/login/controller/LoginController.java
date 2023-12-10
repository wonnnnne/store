package com.devtw.store.domain.login.controller;

import com.devtw.store.domain.login.service.LoginService;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.login.model.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

//        User loginUser = loginService.login(form.getEmail(), passwordEncoder.encode(form.getPassword()));
        User loginUser = loginService.login(form.getEmail(), form.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리

        return "redirect:/";
    }
}
