package com.example.journalApp1.service;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.entity.journalEntryPOJO;
import com.example.journalApp1.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserService {
    @Autowired
    private UserRepository UserRepo;


    public void SaveEntry(User user){
        UserRepo.save(user);
    }

    public List<User> getall(){
        return UserRepo.findAll();
    }

    public Optional<User> findbyid(ObjectId id){
        return UserRepo.findById(id);
    }

    public void deletebyid(ObjectId id){
        UserRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return UserRepo.findByUserName(userName);
    }
}
