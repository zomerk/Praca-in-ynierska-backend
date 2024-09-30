package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.Training;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.TrainingRepository;
import com.example.pracainzynierska.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;


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


}
