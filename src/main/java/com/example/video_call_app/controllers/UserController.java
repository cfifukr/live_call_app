package com.example.video_call_app.controllers;


import com.example.video_call_app.DTOs.UserLoginDto;
import com.example.video_call_app.DTOs.UserLogoutDto;
import com.example.video_call_app.DTOs.UserRegisterDto;
import com.example.video_call_app.models.User;
import com.example.video_call_app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRegisterDto userRegisterDto){
        userService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserLogoutDto userLogoutDto){
        userService.logout(userLogoutDto);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

}
