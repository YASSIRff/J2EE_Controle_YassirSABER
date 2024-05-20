package com.example.book.service;

import com.example.book.dto.UserDto;
import com.example.book.entity.User;


import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}