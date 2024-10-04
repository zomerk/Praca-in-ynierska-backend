package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Complaint")
@Data
public class Complaint {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complaintId;

    private String complaintText;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
