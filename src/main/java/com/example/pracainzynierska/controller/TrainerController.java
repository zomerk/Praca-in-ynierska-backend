package com.example.pracainzynierska.controller;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.Training;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.service.TrainerService;
import com.example.pracainzynierska.service.UserService;
import com.example.pracainzynierska.service.adapter.TrainerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    // Method to get paginated users by trainer
    // Get paginated users for a trainer
    @Autowired
    TrainerService trainerService;
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public Page<User> getUsersByTrainer(
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        TrainerAdapter loggedTrainerAdapter = (TrainerAdapter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Trainer loggedTrainer = loggedTrainerAdapter.getTrainer();
        return trainerService.getUsersByTrainer(loggedTrainer.getTrainerId(), pageable);
    }
    //TODO add training to user
    @PostMapping("/addTraining")
    public ResponseEntity<?> addTraining(@RequestBody Training training, @RequestParam int userId) {
        trainerService.addTraining(training, userId);
        return ResponseEntity.ok().body("Training added successfully");
    }
}
