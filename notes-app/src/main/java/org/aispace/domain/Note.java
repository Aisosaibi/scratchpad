package org.aispace.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.Instant;

@Data
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String title;
    private String body;
    private Instant createdAt;

    public Note() {
    }

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.createdAt = Instant.now();
    }
}


