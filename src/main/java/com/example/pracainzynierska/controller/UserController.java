package com.example.pracainzynierska.controller;

import com.example.pracainzynierska.entity.*;
import com.example.pracainzynierska.service.UserService;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign")
    ResponseEntity<?> signUpToTrainer(@RequestParam Integer trainerId){
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = loggedUserAdapter.getUser();
        userService.signUpToTrainer(loggedUser.getUserId(), trainerId);
        return ResponseEntity.ok().body("User signed up successfully");
    }
    @GetMapping("/training")
    ResponseEntity<?> getTraining(){
        return  userService.getTraining();
    }
    @PostMapping("/feedback")
    ResponseEntity<?> feedbackAfterTraining(@RequestParam Integer trainingId,@RequestBody Feedback feedback){
        return userService.createFeedback(trainingId, feedback);
    }
    @DeleteMapping("feedback")
    ResponseEntity<?> deleteFeedback(@RequestParam Integer trainingId){
        return userService.deleteFeedback(trainingId);
    }
    @PostMapping("/rating")
    ResponseEntity<?> addRating(@RequestParam Integer trainerId, @RequestBody Rating rating){
        return userService.rateYourTrainer(trainerId, rating);
    }
    @GetMapping("/rating")
    public ResponseEntity<?> getRating(@RequestParam int trainerId) {
        return ResponseEntity.ok(userService.getRating(trainerId));

    }
    @PostMapping("/complaint")
    public ResponseEntity<?> postComplaint(int trainerId, @RequestBody Complaint complaint){
        userService.makeComplaint(trainerId, complaint);
        return ResponseEntity.ok("Complaint made");
    }
    @GetMapping("/trainer")
    public ResponseEntity<Boolean> getTrainer(){
        return userService.hasTrainer();
    }

}
