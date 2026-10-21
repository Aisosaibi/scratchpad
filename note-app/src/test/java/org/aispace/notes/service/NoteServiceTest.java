package org.aispace.notes.service;

import org.aispace.notes.domain.Note;
import org.aispace.notes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepository repository;

    @InjectMocks
    NoteService service;

    @Test
    void createNote_savesAndReturnsNoteWithTitleAndContent() {
        when(repository.save(any(Note.class))).thenAnswer(inv -> inv.getArgument(0));

        Note created = service.create("Groceries", "Milk, eggs");

        assertThat(created.getTitle()).isEqualTo("Groceries");
        assertThat(created.getContent()).isEqualTo("Milk, eggs");
        verify(repository).save(any(Note.class));
    }

    @Test
    void createNote_withBlankTitle_isRejected() {
        assertThatThrownBy(() -> service.create("  ", "content"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title");

        verify(repository, never()).save(any());
    }
}