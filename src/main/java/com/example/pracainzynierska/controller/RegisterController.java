package com.example.pracainzynierska.controller;


import com.example.pracainzynierska.converter.DTOconverter;
import com.example.pracainzynierska.dto.AdminDTO;
import com.example.pracainzynierska.dto.TrainerDTO;
import com.example.pracainzynierska.dto.UserDTO;
import com.example.pracainzynierska.service.ComposedDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private ComposedDetailsService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(DTOconverter.convertToUser(user));
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid AdminDTO admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return userService.createAdmin(DTOconverter.convertToAdmin(admin));
    }
    @PostMapping("/register/trainer")
    public ResponseEntity<?> registerTrainer(@RequestBody @Valid TrainerDTO trainer) {
        trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
        return userService.createTrainer(DTOconverter.convertToTrainer(trainer));
    }
    @GetMapping("/trainer")
    public String getTraine() {
        return "siema";
    }
    @GetMapping("/admin")
    public String getTrainer() {
        return "siema";
    }
    @GetMapping("/user")
    public String getTrainers() {
        return "siema";
    }
}

