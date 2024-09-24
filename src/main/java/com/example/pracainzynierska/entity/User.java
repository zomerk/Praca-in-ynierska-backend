package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.FitnessLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private FitnessLevel fitnessLevel;

    private String goal;
    private Timestamp createdAt;


}
