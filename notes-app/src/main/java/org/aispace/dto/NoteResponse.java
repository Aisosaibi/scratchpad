package org.aispace.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class NoteResponse {
    private String id;
    private String title;
    private String body;
    private Instant createdAt;

    public NoteResponse(String id, String title, String body, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }
}
