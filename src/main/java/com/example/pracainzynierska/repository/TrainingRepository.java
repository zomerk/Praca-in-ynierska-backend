package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Integer> {
}
