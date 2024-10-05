package com.example.pracainzynierska.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Training")
@Data
public class Training {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingId;

    private String workoutName;
    private String activityType;
    private LocalDateTime scheduledAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segment> segmentList;

    // New One-to-One relationship with Feedback
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private Feedback feedback;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
