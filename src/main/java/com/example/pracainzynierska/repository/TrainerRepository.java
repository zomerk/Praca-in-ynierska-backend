package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    Optional<Trainer> findByEmail(String email);
}
