package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.Feedback;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.Training;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.repository.FeedbackRepository;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.TrainingRepository;
import com.example.pracainzynierska.repository.UserRepository;
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

}
