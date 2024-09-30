package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TrainerRepository extends PagingAndSortingRepository<Trainer, Integer>, CrudRepository<Trainer, Integer> {
    Optional<Trainer> findByEmail(String email);

    Page<Trainer> findAllByVerifiedIsFalse(Pageable pageable);
    Trainer findByTrainerId(int trainerId);
}
