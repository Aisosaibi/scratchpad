package org.aispace.notes.service;

import org.aispace.notes.domain.Note;
import org.aispace.notes.repository.NoteRepository;

public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Note create(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        return repository.save(new Note(null, title, content));
    }
}