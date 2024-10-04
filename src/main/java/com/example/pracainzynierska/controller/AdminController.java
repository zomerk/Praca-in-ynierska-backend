package com.example.pracainzynierska.controller;

import com.example.pracainzynierska.entity.Complaint;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.repository.TrainerRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TrainerRepository trainerRepository;

    @GetMapping("/unverified")
    public Page<Trainer> getUnverifiedTrainers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "trainerId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        return trainerRepository.findAllByVerifiedIsFalse(PageRequest.of(page, size, sort));
    }
    @PostMapping("/verify")
    public void acceptTrainer(@RequestParam int trainerId,@RequestParam Boolean verified) {
        if(verified) {
            var trainer = trainerRepository.findByTrainerId(trainerId);
            trainer.setVerified(true);
            trainerRepository.save(trainer);
        }
        else {
            var trainer = trainerRepository.findByTrainerId(trainerId);
            trainer.setVerified(false);
            trainer.setActive(false);
            trainerRepository.save(trainer);
        }
    }
    @GetMapping("/complaints")
    public Page<Complaint> getComplaints( @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "complaintId") String sortBy,
                                          @RequestParam(defaultValue = "asc") String sortDir){
        return trainerRepository.findAllByVerifiedIsFalse(PageRequest.of(page, size, sort));

    }
}
