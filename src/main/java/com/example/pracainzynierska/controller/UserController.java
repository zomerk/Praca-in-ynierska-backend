package com.example.pracainzynierska.controller;


import com.example.pracainzynierska.converter.DTOconverter;
import com.example.pracainzynierska.dto.UserDTO;
import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private CustomUserDetailsService userService;


    @PostMapping("/register/user")
    public ResponseEntity registerUser(@RequestBody @Valid UserDTO user) {
        return userService.createUser(DTOconverter.convertToUser(user));
    }
    @PostMapping("/register/admin")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return userService.createAdmin(admin);
    }
    @PostMapping("/register/trainer")
    public Trainer registerTrainer(@RequestBody Trainer trainer) {
        return userService.createTrainer(trainer);
    }
    @GetMapping("/user")
    public String checkUser(){
        return "I am user";
    }
    @GetMapping("/admin")
    public String checkAdmin(){
        return "I am admin";
    }
    @GetMapping("/trainer")
    public String checkTrainer(){
        return "I am trainer";
    }
}

