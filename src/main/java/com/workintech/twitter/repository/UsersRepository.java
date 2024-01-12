package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.email=:email")
    Optional<Users> findByEmail(String email);
}
