package com.example.backend.service;

import com.example.backend.dto.LoginData;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.UserDtoReturn;

public interface UserService {
    UserDtoReturn register (UserDto userDto);
    LoginData login (UserDto userDto);
}
