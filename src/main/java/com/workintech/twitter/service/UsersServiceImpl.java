package com.workintech.twitter.service;

import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exceptions.TwitterException;
import com.workintech.twitter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    private UsersRepository usersRepository;
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users find(long id) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new TwitterException("User with given id is not exist : " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Users delete(long id) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            usersRepository.delete(user);
            return user;
        } else {
            throw new TwitterException("User with given id is not exist : " + id, HttpStatus.NOT_FOUND);
        }
    }
}
