package com.app.restrobuddyuser.controller;

import com.app.restrobuddyuser.dto.LoginDto;
import com.app.restrobuddyuser.dto.UserDto;
import com.app.restrobuddyuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public UserDto saveUser(@RequestBody UserDto user){
        return userService.saveUser(user);
    }

    @GetMapping("/getUser/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public boolean deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}
