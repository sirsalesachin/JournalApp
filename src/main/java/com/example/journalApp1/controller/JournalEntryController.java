package com.example.journalApp1.controller;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.entity.journalEntryPOJO;
import com.example.journalApp1.service.JournalEntryService;
import com.example.journalApp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAlljournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<journalEntryPOJO> all = user.getJournalEntries();
        if(all != null  && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<journalEntryPOJO> CreateEntry(@RequestBody journalEntryPOJO myEntry, @PathVariable String userName){
        try{
            journalEntryService.SaveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("id/{myid}")
    public Optional<journalEntryPOJO> getById(@PathVariable ObjectId myid){
        return journalEntryService.findbyid(myid);
    }

    @DeleteMapping("id/{userName}/{myid}")
    public Boolean Deletebyid(@PathVariable ObjectId myid, @PathVariable String userName) {
        journalEntryService.deletebyid(myid, userName);
        return true;
    }

    @PutMapping("id/{userName}/{myid}")
    public journalEntryPOJO updatejournalbyid(@PathVariable ObjectId myid,
                                              @RequestBody journalEntryPOJO newEntry,
                                              @PathVariable String userName){
        journalEntryPOJO old = journalEntryService.findbyid(myid).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.SaveEntry(old);
        return old;
    }
}
