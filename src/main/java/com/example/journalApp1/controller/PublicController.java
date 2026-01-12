package com.example.journalApp1.controller;

import com.example.journalApp1.entity.User;
import com.example.journalApp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private UserService userService;


    @PostMapping
    public void createuser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
