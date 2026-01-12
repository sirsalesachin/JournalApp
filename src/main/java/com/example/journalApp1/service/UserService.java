package com.example.journalApp1.service;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {


    @Autowired
    private UserRepository UserRepo;

    public List<User> GetAll(){
        return UserRepo.findAll();
    }


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        UserRepo.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        UserRepo.save(user);
    }

    public void saveUser(User user){
        UserRepo.save(user);
    }


    public User findByUserName(String userName){
        return UserRepo.findByUserName(userName);
    }

//    public void DeleteByID(ObjectId userID){
//        UserRepo.deleteById(userID);
//    }
}
