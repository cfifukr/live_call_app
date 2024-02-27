package com.example.video_call_app.services;


import com.example.video_call_app.DTOs.UserLoginDto;
import com.example.video_call_app.DTOs.UserLogoutDto;
import com.example.video_call_app.DTOs.UserRegisterDto;
import com.example.video_call_app.models.User;
import com.example.video_call_app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    @Transactional
    public void  register(UserRegisterDto userRegisterDto){
        String passHash = DigestUtils.sha256Hex(userRegisterDto.getPassword());


        User user = User.builder()
                .email(userRegisterDto.getEmail())
                .username(userRegisterDto.getUsername())
                .password(passHash)
                .status("online").build();

        userRepository.save(user);

    }


    @Transactional
    public User login(UserLoginDto userLoginDto){
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if(user == null){
            throw new RuntimeException("UserNotFound");
        }


        if(user.getPassword().compareTo(DigestUtils.sha256Hex(userLoginDto.getPassword())) != 0){
            throw new RuntimeException("WrongPassword");
        }

        user.setStatus("online");
        userRepository.save(user);
        return user;
    }


    @Transactional
    public void logout(UserLogoutDto userLogoutDto){
        User user = userRepository.findByEmail(userLogoutDto.getEmail());
        if(user == null){
            throw new RuntimeException("UserNotFound");
        }

        user.setStatus("offline");
        userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public List<User> findAll(){


        return userRepository.findAll();
    }


}
