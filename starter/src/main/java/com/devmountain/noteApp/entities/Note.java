package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Notes")
//can use lombok to simplify code using the following three annotations to generate getters, setters, and constructors
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    //private member variable named user
    //many to one creates an association within hibernate
    @ManyToOne
    //jsonBackReference prevents infinite recursion when you deliver the resource up as JSON through the rest API endpoint
    @JsonBackReference
    private User user;

    //custom constructor inside entity that accepts a DTO argument
    public Note(NoteDto noteDto){
        if (noteDto.getBody() != null){
            this.body = noteDto.getBody();
        }
    }

}
