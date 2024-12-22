package com.example.todo.service;

import com.example.todo.dto.NoteDto;
import com.example.todo.exception.NoteNotFoundException;
import com.example.todo.model.Note;
import com.example.todo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(NoteDto noteDto) {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setCategory(noteDto.getCategory());
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByCategory(String category) {
        return noteRepository.findByCategory(category);
    }

    public Note updateNote(Long id, NoteDto noteDto) {
        Note note = getNoteById(id);
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setCategory(noteDto.getCategory());
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id) {
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }
}
