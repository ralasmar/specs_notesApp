package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    //NEW METHOD TO REGISTER A USER-------------------------------------------------
    // use transactional anytime you save something to database to ensure the transaction gets opened when datasource gets resolved
    @Transactional
    List<String> addUser(UserDto userDto);

    //NEW METHOD TO HANDLE USER LOGIN-----------------------------------------------
    List<String> userLogin(UserDto userDto);
}
