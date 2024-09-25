package com.example.pracainzynierska.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Training")
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingId;

    private String workoutName;
    private String activityType;
    private LocalDate scheduledAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
