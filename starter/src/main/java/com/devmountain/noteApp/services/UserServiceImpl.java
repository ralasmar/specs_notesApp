package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //spring boot can find corresponding dependency for the repository by using autowired annotation
    @Autowired
    private UserRepository userRepository;
    //autowire passwordEncoder bean into UserServiceImpl
    @Autowired
    private PasswordEncoder passwordEncoder;
    //NEW METHOD TO REGISTER A USER-------------------------------------------------
    // use transactional anytime you save something to database to ensure the transaction gets opened when datasource gets resolved
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        //initialize new variable of type List<String>
        List<String> response = new ArrayList<>();
        //create new method of type User called user
        User user = new User(userDto);
        //invoke saveAndFlush() method available in userRepository and pass in user object
        userRepository.saveAndFlush(user);
        //add a string to response with a success message
        response.add("User successfully added");
        return response;
    }
    //NEW METHOD TO HANDLE USER LOGIN-----------------------------------------------
    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        //optionals are a way to avoid NullPointerExceptions which will break your code
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        //conditional logic to check to see if userOptional is present
        if(userOptional.isPresent()){
            response.add("User Login Successful");
            response.add(String.valueOf(userOptional.get().getId()));
        }
        else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}
