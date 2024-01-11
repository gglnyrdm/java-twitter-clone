package com.workintech.twitter.service;

import com.workintech.twitter.entity.Users;

import java.util.List;

public interface UsersService {
    Users save(Users user);
    List<Users> findAll();
    Users find(long id);
    Users delete(long id);
}
