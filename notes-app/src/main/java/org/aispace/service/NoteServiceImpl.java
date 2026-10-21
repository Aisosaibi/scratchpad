package org.aispace.service;

import org.aispace.domain.Note;
import org.aispace.exception.NoteException;
import org.aispace.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public Note create(String title, String body) {
        Note note = new Note(title, body);
        return noteRepository.save(note);
    }

    @Override
    public Optional<Note> findById(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note update(String id, String title, String body) throws NoteException {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteException("Note not found"));
        note.setTitle(title);
        note.setBody(body);
        return noteRepository.save(note);
    }


    @Override
    public void delete(String id) {
        noteRepository.deleteById(id);
    }
}
