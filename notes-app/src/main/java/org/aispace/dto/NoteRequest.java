package org.aispace.dto;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String body;

    public NoteRequest() {
    }
}
