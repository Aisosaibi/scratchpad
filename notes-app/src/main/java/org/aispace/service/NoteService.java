package org.aispace.service;

import org.aispace.domain.Note;
import org.aispace.exception.NoteException;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note create(String title ,String body);
    Optional<Note> findById(String id);
    List<Note> findAll();
    Note update(String id, String title, String body) throws NoteException;
    void delete(String id);
}
