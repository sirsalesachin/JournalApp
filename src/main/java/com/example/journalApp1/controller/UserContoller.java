package com.example.journalApp1.controller;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserContoller {


    @Autowired
    private UserService userService;


    @PostMapping
    public void CreateUser(@RequestBody User user){
        userService.SaveEntry(user);
    }

    @GetMapping
    public List<User> Getall(){
        return userService.getall();
    }

    @GetMapping("id/{myid}")
    public User Findbyid(@PathVariable ObjectId myid){
        return userService.findbyid(myid).orElse(null);
    }

    @DeleteMapping("id/{myid}")
    public boolean deletebyid(@PathVariable ObjectId myid){
        userService.deletebyid(myid);
        return true;
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> UpdateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.SaveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
