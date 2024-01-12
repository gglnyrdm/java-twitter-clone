package com.workintech.twitter.service;

import com.workintech.twitter.entity.UserRole;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exceptions.TwitterException;
import com.workintech.twitter.repository.UserRoleRepository;
import com.workintech.twitter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UsersRepository usersRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthenticationService(UsersRepository usersRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(String email, String password, String firstName, String lastName,String username){
        Optional<Users> foundUser = usersRepository.findByEmail(email);
        if (foundUser.isPresent()){
            throw new TwitterException("User with given email already exist, please login: " + email, HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserRole userRole = userRoleRepository.findByAuthority("USER").orElseGet(() -> {
            UserRole newUserRole = new UserRole();
            newUserRole.setAuthority("USER");
            return userRoleRepository.save(newUserRole);
        });

        Users user = new Users();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(userRole);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        return usersRepository.save(user);
    }
}
