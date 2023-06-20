package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//designates class level path that further endpoints will begin at
@RequestMapping("/api/v1/users")
public class UserController {
    //autowire our dependencies which are the UserService bc controllers interact with service layers and PasswordEncoder so we can hash incoming passwords
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //method that will handle POST requests to be able to register a user
    @PostMapping("/register")
    //RequestBody annotation so that the JSON body object on the request gets mapped to our DTO and becomes usable
    public List<String>addUser(@RequestBody UserDto userDto){
        //hash the incoming password, encode method available in the passWord encoder dependency
        String passHash = passwordEncoder.encode(userDto.getPassword());
        //invoke setPassword method and pass in the passHash variable
        userDto.setPassword(passHash);
        //return the addUser method (available in userService dependency) and pass in the userDto argument
        return userService.addUser(userDto);
    }
    //method that will log a user in using another POST request
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
