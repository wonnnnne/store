package com.devtw.store.domain.login.service;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.common.JwtProvider;
import com.devtw.store.domain.login.model.LoginResponse;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ApiResponse<LoginResponse> login(Map<String,String> param) {

        String email = param.get("email");
        String password = param.get("password");

        if (!isJoinedUser(email)) {
            return ApiResponse.fail("회원이 아닙니다. 회원가입을 먼저 진행해주세요.");
        }

        User user = checkPassword(email, password);

        if (user == null) {
            return  ApiResponse.fail("비밀번호가 일치하지 않습니다. 비밀번호를 확인 후 다시 진행해주세요.");
        }

        String jwtToken = jwtProvider.createToken(user);

        LoginResponse loginResponse = new LoginResponse(user, jwtToken);

        return ApiResponse.success(loginResponse);
    }

    public Boolean isJoinedUser(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    public User checkPassword(String email, String password) {
        User targetUser = userRepository.findByEmail(email);
        if (targetUser.getPassword().equals(password)) {
            return targetUser;
        } else {
            return null;
        }
    }
}
