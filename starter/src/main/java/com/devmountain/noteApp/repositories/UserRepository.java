package com.devmountain.noteApp.repositories;

import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this annotation clues spring boot in to keep track of this resource for dependency injection
@Repository
//extend JpaRepository which accepts two type arguments, <Type,Id type>
public interface UserRepository extends JpaRepository<User, Long> {
   //will search users table for the user with the username that matches the given string
    Optional<User> findByUsername(String username);
}
