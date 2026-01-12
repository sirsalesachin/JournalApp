package com.example.journalApp1.service;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.entity.journalEntryPOJO;
import com.example.journalApp1.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    @Transactional
    public void SaveEntry(journalEntryPOJO journalEntryPOJO, String userName){
        User user = userService.findByUserName(userName);
        journalEntryPOJO saved = journalEntryRepository.save(journalEntryPOJO);
        user.getJournalEntries().add(saved);
//        User.userService.SetUserName(user);
        userService.saveUser(user);
    }

    public void SaveEntry(journalEntryPOJO journalEntryPOJO){
        journalEntryRepository.save(journalEntryPOJO);
    }

    public List<journalEntryPOJO> getall(){
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntryPOJO> findbyid(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deletebyid(ObjectId id, String userName){
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error while deleteing the entry. " , e);
        }
        return removed;
    }

}
