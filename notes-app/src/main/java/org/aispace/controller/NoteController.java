package org.aispace.controller;

import org.aispace.domain.Note;
import org.aispace.dto.NoteRequest;
import org.aispace.dto.NoteResponse;
import org.aispace.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(@RequestBody NoteRequest request) {
        Note note = noteService.create(request.getTitle(), request.getBody());
        return ResponseEntity.ok(toResponse(note));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getById(@PathVariable String id) {
        return noteService.findById(id)
                .map(note -> ResponseEntity.ok(toResponse(note)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(), note.getBody(), note.getCreatedAt());
    }
}
