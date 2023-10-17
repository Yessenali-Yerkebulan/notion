package com.example.repositories;

import com.example.models.Note;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;

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
}
