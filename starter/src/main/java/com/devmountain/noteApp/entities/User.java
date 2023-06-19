package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //new private member variable and annotate it with column annotation and the unique option set to true
    @Column(unique = true)
    private String username;
    //new private variable with column annotation with no options
    @Column
    private String password;
    //private member variables only accessible within the class they reside in, need getters and setters

    //annotation with mapped, fetch, and cascade types
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //handle the other half of mitigating infinite recursion
    @JsonManagedReference
    //private set of type Note set equal to a new HashSet
    private Set<Note> noteSet = new HashSet<>();

    //constructor that accepts DTO argument
    public User(UserDto userDto){
        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
    }
}
