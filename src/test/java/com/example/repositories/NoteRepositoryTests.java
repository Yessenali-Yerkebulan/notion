package com.example.repositories;

import com.example.models.Note;
import static org.assertj.core.api.Assertions.assertThat;

import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class NoteRepositoryTests {
    @Autowired
    private NoteRepository noteRepository;

    private Note note;

    @BeforeEach
    public void setup(){
        note = Note.builder()
                .title("Note 1")
                .content("Content 1")
                .createdAt(Date.valueOf("2023-08-15"))
                .build();
    }

    @Test
    @DisplayName("JUnit test for save note operation")
    public void givenNoteObject_whenSave_thenReturnSavedNote(){
        Note savedNote = noteRepository.save(note);
        assertThat(savedNote).isNotNull();
        assertThat(savedNote.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("JUnit test for get all notes operation")
    public void givenNotesList_whenFindAll_thenNotesList(){
        Note note2 = Note.builder()
                .title("Note 2")
                .content("Content 2")
                .createdAt(Date.valueOf("2023-05-09"))
                .build();

        noteRepository.save(note);
        noteRepository.save(note2);

        List<Note> notes = noteRepository.findAll();
        assertThat(notes).isNotNull();
        assertThat(notes.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for get note by id operation")
    public void givenNoteObject_whenFindById_thenReturnNoteObject(){
        noteRepository.save(note);
        Note noteDB = noteRepository.findById(note.getId()).get();
        assertThat(noteDB).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for update note operation")
    public void givenNoteObject_whenUpdateNote_thenReturnUpdatedNote(){
        noteRepository.save(note);
        Note savedNote = noteRepository.findById(note.getId()).get();
        savedNote.setTitle("Updated Note");
        savedNote.setContent("Updated Content");
        Note updatedNote = noteRepository.save(savedNote);
        assertThat(updatedNote.getTitle()).isEqualTo("Updated Note");
        assertThat(updatedNote.getContent()).isEqualTo("Updated Content");
    }

    @Test
    @DisplayName("JUnit test for delete note operation")
    public void givenNoteObject_whenDelete_thenRemoveNote(){
        noteRepository.save(note);
        noteRepository.delete(note);
        Optional<Note> noteOptional = noteRepository.findById(note.getId());
        assertThat(noteOptional).isEmpty();
    }

    @Test
    @DisplayName("JUnit test for delete note by id operation")
    public void givenNoteObject_whenDeleteById_thenRemoveNote(){
        noteRepository.save(note);
        noteRepository.deleteById(note.getId());
        Optional<Note> noteOptional = noteRepository.findById(note.getId());
        assertThat(noteOptional).isEmpty();
    }
}
