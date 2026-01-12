package com.example.journalApp1.controller;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("AllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<User> all =userService.GetAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("CreateAdmin")
    public void CreateNewAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
