package com.example.pracainzynierska.controller;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.service.UserService;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    ResponseEntity<?> signUpToTrainer(@RequestParam Integer trainerId){
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = loggedUserAdapter.getUser();
        userService.signUpToTrainer(loggedUser.getUserId(), trainerId);
        return ResponseEntity.ok().body("User signed up successfully");
    }

}
