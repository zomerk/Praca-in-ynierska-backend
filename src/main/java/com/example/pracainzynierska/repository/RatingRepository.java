package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Rating;
import com.example.pracainzynierska.entity.Trainer;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    Boolean existsByUserIdAndTrainer(Integer userId, Trainer trainer);
}
