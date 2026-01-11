package com.example.journalApp1.repository;

import com.example.journalApp1.entity.journalEntryPOJO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<journalEntryPOJO, ObjectId> {
}
