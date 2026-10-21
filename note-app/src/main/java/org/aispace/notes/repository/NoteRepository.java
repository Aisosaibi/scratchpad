package org.aispace.notes.repository;

import org.aispace.notes.domain.Note;

public interface NoteRepository {
    Note save(Note note);
}