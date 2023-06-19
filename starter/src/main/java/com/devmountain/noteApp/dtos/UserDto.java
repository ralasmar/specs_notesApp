package com.devmountain.noteApp.dtos;

import com.devmountain.noteApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
//need to implement Serializable interface to allow class to be converted to a bytestream and sent outside application
public class UserDto  implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<NoteDto> noteDtoSet = new HashSet();

    //constructor that accepts a User object as argument and maps the DTO object fields accordingly
    public UserDto(User user){
        //includes conditional logic to prevent null pointer exceptions (known as sanity checking)
        if(user.getId() != null){
            this.id = user.getId();
        }
        if(user.getUsername() != null){
            this.username = user.getUsername();
        }
        if(user.getPassword() != null){
            this.password = user.getPassword();
        }
    }
}
