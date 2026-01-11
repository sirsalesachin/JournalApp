package com.example.journalApp1.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
public class journalEntryPOJO {
        @Id
        private ObjectId id;
        private String title;
        private String content;
        private LocalDateTime date;

}
