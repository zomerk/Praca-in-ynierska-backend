package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.FitnessLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Users Table")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private FitnessLevel fitnessLevel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainer trainer;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Training> trainings;
    private String goal;
    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
