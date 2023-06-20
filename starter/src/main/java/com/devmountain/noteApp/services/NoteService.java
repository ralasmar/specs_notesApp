package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    //adding a note
    @Transactional
    void addNote(NoteDto noteDto, Long userId);

    //deleting a note
    @Transactional
    void deleteNoteById(Long noteId);

    //updating a note
    @Transactional
    void updateNoteById(NoteDto noteDto);

    //method for finding all notes
    List<NoteDto> getAllNotesByUserId(Long userId);

    //method for getting a Note by the note id
    Optional<NoteDto> getNoteById(Long noteId);
}
