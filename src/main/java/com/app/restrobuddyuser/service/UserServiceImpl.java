package com.app.restrobuddyuser.service;

import com.app.restrobuddyuser.dto.LoginDto;
import com.app.restrobuddyuser.dto.UserDto;
import com.app.restrobuddyuser.entity.User;
import com.app.restrobuddyuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::copyToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(this::copyToDto).orElse(null);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (Objects.isNull(user)) {
            user = new User();
        }
        if (validateUser(user)) {
            // Validation Success
        }
        copyToEntity(userDto, user);
        User savedUser = userRepository.save(user);
        return copyToDto(savedUser);
    }

    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;

    }

    public boolean validateUser(User user) {
        return true;
    }

    public UserDto login(LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getEmail());
        if(Objects.nonNull(user) && Objects.equals(user.getPassword(), loginDto.getPassword())){
            return copyToDto(user);
        }else{
            return null;
        }
    }

    public UserDto copyToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public void copyToEntity(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
    }
}
