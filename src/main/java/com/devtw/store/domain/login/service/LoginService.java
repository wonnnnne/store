package com.devtw.store.domain.login.service;

import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public Boolean isJoinedUser(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
