package com.example.pracainzynierska.controller;


import com.example.pracainzynierska.converter.DTOconverter;
import com.example.pracainzynierska.dto.*;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.service.AuthenticationService;
import com.example.pracainzynierska.service.ComposedDetailsService;
import com.example.pracainzynierska.service.jwt.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthContorller {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authenticationService.signUpUser(DTOconverter.convertToUser(user));
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid AdminDTO admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return authenticationService.signUpAdmin(DTOconverter.convertToAdmin(admin));
    }
    @PostMapping("/register/trainer")
    public ResponseEntity<?> registerTrainer(@RequestBody @Valid TrainerDTO trainer) {
        trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
        return authenticationService.signUpTrainer(DTOconverter.convertToTrainer(trainer));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody @Valid LoginDTO loginUserDto) {
        UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken,jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}

