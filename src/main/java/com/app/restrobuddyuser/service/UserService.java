package com.app.restrobuddyuser.service;

import com.app.restrobuddyuser.dto.LoginDto;
import com.app.restrobuddyuser.dto.UserDto;

import java.util.List;


public interface UserService {
    List<UserDto> getAllUser();

    UserDto getUser(Long userId);

    UserDto saveUser(UserDto userDto);

    boolean deleteUser(Long userId);

    UserDto login(LoginDto loginDto);
}
