package com.example.services;

import com.example.models.Note;
import com.example.repositories.NoteRepository;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note createNote(Note note);

    List<Note> findAll();
    Optional<Note> findOne(Long id);
}
