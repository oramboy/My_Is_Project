package com.example.todo.controller;

import com.example.todo.dto.NoteDto;
import com.example.todo.model.Note;
import com.example.todo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody NoteDto noteDto) {
        return noteService.createNote(noteDto);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/category/{category}")
    public List<Note> getNotesByCategory(@PathVariable String category) {
        return noteService.getNotesByCategory(category);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {
        return noteService.updateNote(id, noteDto);
    }

    @DeleteMapping("/{id}")
    public String deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return "Note deleted successfully with id: " + id;
    }
}
