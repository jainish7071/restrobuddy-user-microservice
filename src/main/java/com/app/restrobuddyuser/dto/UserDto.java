package com.app.restrobuddyuser.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
}
