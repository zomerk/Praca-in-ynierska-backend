package com.example.pracainzynierska.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Rating")
@Data
public class Rating {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    private Integer ratingValue;
    private String comment;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @JsonIgnore
    private Integer userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
