package com.example.services.impl;

import com.example.models.Note;
import com.example.services.NoteService;

import java.util.List;
import java.util.Optional;

public class NoteServiceImpl implements NoteService {


    @Override
    public Note createNote(Note note) {
        return null;
    }

    @Override
    public List<Note> findAll() {
        return null;
    }

    @Override
    public Optional<Note> findOne(Long id) {
        return Optional.empty();
    }
}
