package com.example.pracainzynierska.controller;


import com.example.pracainzynierska.converter.DTOconverter;
import com.example.pracainzynierska.dto.AdminDTO;
import com.example.pracainzynierska.dto.TrainerDTO;
import com.example.pracainzynierska.dto.UserDTO;
import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private CustomUserDetailsService userService;


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO user) {
        return userService.createUser(DTOconverter.convertToUser(user));
    }
    @PostMapping("/admin")
    public Admin registerAdmin(@RequestBody @Valid AdminDTO admin) {
        return userService.createAdmin(DTOconverter.convertToAdmin(admin));
    }
    @PostMapping("/trainer")
    public Trainer registerTrainer(@RequestBody @Valid TrainerDTO trainer) {
        return userService.createTrainer(DTOconverter.convertToTrainer(trainer));
    }
}

