package com.workintech.twitter.repository;

import com.workintech.twitter.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT u FROM UserRole u WHERE u.authority=:authority")
    Optional<UserRole> findByAuthority(String authority);
}
