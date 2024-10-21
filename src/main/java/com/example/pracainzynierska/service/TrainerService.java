package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.*;
import com.example.pracainzynierska.repository.SegmentRepository;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.TrainingRepository;
import com.example.pracainzynierska.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    SegmentRepository segmentRepository;


    // Method to get paginated users by trainer
    public Page<User> getUsersByTrainer(Integer trainerId, Pageable pageable) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        return userRepository.findByTrainer(trainer, pageable);
    }

    public void addTraining(Training training, int userId) {
        User user = userRepository.findById(userId).get();
        training.setUser(user);
        trainingRepository.save(training);
        var listOfSegmetns = training.getSegmentList();
        for(Segment segment : listOfSegmetns) {
            segment.setTraining(training);
            segmentRepository.save(segment);
        }
    }
    public void deleteTraining(Integer trainingId) {
        Training tr = trainingRepository.findById(trainingId).get();
        tr.getSegmentList().clear();
        trainingRepository.deleteById(trainingId);
    }

    public Feedback getFeedback(int trainingId) {
        Training training = trainingRepository.findById(trainingId).get();
        return training.getFeedback();
    }

    public Page<Trainer> getPaginatedTrainers(Pageable pageable) {
        return trainerRepository.findAll(pageable);
    }
}
