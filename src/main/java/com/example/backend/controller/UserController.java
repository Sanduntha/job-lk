package com.example.backend.controller;

import com.example.backend.dto.LoginData;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.UserDtoReturn;
import com.example.backend.service.UserService;
import com.example.backend.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoReturn> register (@RequestBody UserDto userDto) {
        UserDtoReturn register = userService.register(userDto);
        System.out.println("Ok");

        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginData> login (@RequestBody UserDto userDto) {
//
//        LoginData login = userService.login(userDto);
//        return new ResponseEntity<>(login, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserDto userDto) {
        try {
            LoginData login = userService.login(userDto);
            return new ResponseEntity<>(login, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return 401 Unauthorized with error message for frontend to show
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginData(e.getMessage(), null, null));
        }
    }


}
