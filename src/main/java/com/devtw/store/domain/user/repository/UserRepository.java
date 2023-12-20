package com.devtw.store.domain.user.repository;

import com.devtw.store.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.id = :id")
//    User findUserById(String id);


    User findByEmail(String email);


}
