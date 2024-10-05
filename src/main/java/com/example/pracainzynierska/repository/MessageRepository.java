package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Message;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUserAndTrainerAndUserSenderIsTrue(User user, Trainer trainer);
    List<Message> findByTrainerAndUserAndUserSenderIsFalse(Trainer trainer, User user);


}
