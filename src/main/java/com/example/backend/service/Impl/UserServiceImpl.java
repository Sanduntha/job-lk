package com.example.backend.service.Impl;

import com.example.backend.dto.LoginData;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.UserDtoReturn;
import com.example.backend.entity.User;
import com.example.backend.repo.JobRepo;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.UserService;
import com.example.backend.util.JWTTokenGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JWTTokenGenerator jwtTokenGenerator;

//    @Autowired
//    private JobRepo jobRepo;

    @Autowired //dependency inject karana eka
    public UserServiceImpl(UserRepo userRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.userRepo = userRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @Override
    public LoginData login(UserDto userDto) {
        Optional<User> userByEmail = userRepo.getUserByEmail(userDto.getEmail());

        if (userByEmail.isPresent()) {
            User user = userByEmail.get();

            // Decode Base64 password stored in DB
            String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));

            // Compare with input password
            if (userDto.getPassword().equals(decodedPassword)) {
                String token = jwtTokenGenerator.generateToken(user);
                return new LoginData(user.getEmail(), token, user.getRole());
            } else {
                throw new RuntimeException("Invalid password");
            }
        }

        throw new RuntimeException("User not found");
    }

//    public LoginData login(UserDto userDto) {
//        Optional<User> userByEmail = userRepo.getUserByEmail(userDto.getEmail());
//
//        if (userByEmail.isPresent()) {
//            User user = userByEmail.get();
//
////             Decode password
//            byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
//            String decodedPassword = new String(decodedBytes);
//
//            // Check password AND role
//            if (userDto.getPassword().equals(decodedPassword)) {
//                String token = jwtTokenGenerator.generateToken(user);
//                return new LoginData(user.getEmail(), token, user.getRole());
//            } else {
//                // Password or role did not match
//                return new LoginData("Invalid email, password or role", null,"User");
//            }
//        }

        // Email not found
//        return new LoginData("Email not found"  , null, userDto.getRole());


    @Override
    public UserDtoReturn register(UserDto userDto) {
        String encodedPassword = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());
        User savedUser = userRepo.save(new User(null, userDto.getName(), userDto.getEmail(), encodedPassword, userDto.getRole()));

        if (savedUser != null && savedUser.getEmail() != null) {
            return new UserDtoReturn(savedUser.getEmail(), "registration successful");
        }
        return new UserDtoReturn(null, "registration failed");
    }

}

