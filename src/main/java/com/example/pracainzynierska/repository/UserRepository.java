package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Page<User> findByTrainer(Trainer trainer, Pageable pageable);

}
