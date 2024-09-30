package com.example.pracainzynierska.entity;

import com.example.pracainzynierska.enums.DurationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Segments")
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer segmentId;

    private String segmentName;

    @Enumerated(EnumType.STRING)
    private DurationType durationType;

    private Integer durationValue;
    private String intensity;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}


