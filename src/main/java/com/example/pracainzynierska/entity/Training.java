package com.example.pracainzynierska.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "Training")
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingId;

    private String workoutName;
    private String activityType;
    private Timestamp createdAt;
    private Timestamp scheduledAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
