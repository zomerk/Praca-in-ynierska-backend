package com.example.pracainzynierska.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "Trainer")
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerId;

    private String firstName;
    private String lastName;
    private String specialization;
    private String experienceLevel;
    private String email;
    private String password;
    private Boolean verified = Boolean.FALSE;
    private Boolean active = Boolean.TRUE;
}