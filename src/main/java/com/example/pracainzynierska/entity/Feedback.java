package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.Feeling;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Feedback")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    private String feedbackText;

    @Enumerated(EnumType.STRING)
    private Feeling feeling;

    private Integer ratingValue;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    // Getters and Setters
}

