package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.*;
import com.example.pracainzynierska.repository.*;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ComplaintRepository complaintRepository;


    public void signUpToTrainer(Integer userId, Integer trainerId) {
        // Find the user and trainer by their IDs
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        // Associate the user with the trainer
        user.setTrainer(trainer);

        // Save the updated user entity
        userRepository.save(user);
    }


    public ResponseEntity<?> getTraining() {
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = loggedUserAdapter.getUser();
        return ResponseEntity.ok(loggedUser.getTrainings());
    }

    public ResponseEntity<?> createFeedback(Integer trainingId,Feedback feedback) {
        Training tr = trainingRepository.findById(trainingId).get();
        feedback.setTraining(tr);
        feedbackRepository.save(feedback);
        return ResponseEntity.ok(feedback);
    }
    public ResponseEntity<?> deleteFeedback(Integer trainingId) {
        Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();

            Feedback feedback = training.getFeedback();
            if (feedback != null) {
                // Remove feedback reference from training and delete feedback
                training.setFeedback(null);
                feedbackRepository.delete(feedback);
                trainingRepository.save(training);
            }
        } else {
            throw new RuntimeException("Training not found with ID: " + trainingId);
        }
        return ResponseEntity.ok().body("Feedback successfully deleted");
    }
    public ResponseEntity<?> rateYourTrainer(int TrainerId, Rating rating) {
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = loggedUserAdapter.getUser();
        if(TrainerId != loggedUser.getTrainer().getTrainerId()){
            throw new RuntimeException("You cannot rate trainer that doesnt train you");
        }
        Trainer trainer = trainerRepository.findById(TrainerId).get();
        if(ratingRepository.existsByUserIdAndTrainer(loggedUser.getUserId(),trainer)){
            throw new RuntimeException("You can only do one rating");
        }
        rating.setTrainer(trainer);
        rating.setUserId(loggedUser.getUserId());
        ratingRepository.save(rating);
        return ResponseEntity.ok("Rating created successfully");
    }
    public ResponseEntity<?> getRating(int trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).get();
        return ResponseEntity.ok(trainer.getRatingList());
    }
    public ResponseEntity<?> makeComplaint(int trainerId, Complaint complaint) {
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = loggedUserAdapter.getUser();
        if(trainerId != loggedUser.getTrainer().getTrainerId()){
            throw new RuntimeException("You cannot make complaint that doesnt train you");
        }
        Trainer trainer = trainerRepository.findById(trainerId).get();
        complaint.setTrainer(trainer);
        complaintRepository.save(complaint);
        return ResponseEntity.ok(complaint);

    }
}
