package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "Complaint")
@Data
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complaintId;

    private String complaintText;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Timestamp createdAt;
    private Timestamp resolvedAt;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;



    // Getters and Setters
}
