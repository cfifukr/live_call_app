package com.example.video_call_app.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterDto {

    private String username;

    private String email;

    private String password;

}
