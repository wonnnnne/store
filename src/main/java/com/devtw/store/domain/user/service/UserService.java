package com.devtw.store.domain.user.service;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ApiResponse<User> registerUser(User user) {
        User searchUser = getUserByEmail(user.getEmail());

        if (searchUser == null) {
            User joinedUser = saveUser(user);
            return ApiResponse.success(joinedUser);
        } else {
            return ApiResponse.fail("이미 가입된 정보입니다.");
        }
    }

}
