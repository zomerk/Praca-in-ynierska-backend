package com.example.pracainzynierska.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Rating> ratingList;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Complaint> complaintsList;
}