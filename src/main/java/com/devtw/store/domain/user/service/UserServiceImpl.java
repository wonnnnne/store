package com.devtw.store.domain.user.service;

import com.devtw.store.domain.user.model.User;
import com.devtw.store.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service            // 내가 서비스다
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void join(User user) {
        userRepository.save(user);
    }
}
