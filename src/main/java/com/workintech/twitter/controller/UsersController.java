package com.workintech.twitter.controller;

import com.workintech.twitter.dto.UsersDto;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.service.UsersService;
import com.workintech.twitter.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/")
    public Users save(@RequestBody Users user){
        return usersService.save(user);
    }

    @GetMapping("/")
    public List<UsersDto> findAll(){
        List<Users> users = usersService.findAll();
        List<UsersDto> usersDtoList = new ArrayList<>();

        for (Users user : users){
            UsersDto usersDto = EntityConverter.findResults(user);
            usersDtoList.add(usersDto);
        }
        return usersDtoList;
    }

    @GetMapping("/{id}")
    public UsersDto find(@PathVariable long id){
        Users user = usersService.find(id);
        return EntityConverter.findResults(user);
    }

    @DeleteMapping("/{id}")
    public UsersDto delete(@PathVariable long id){
        Users deletedUser = usersService.delete(id);
        return EntityConverter.findResults(deletedUser);
    }
}
