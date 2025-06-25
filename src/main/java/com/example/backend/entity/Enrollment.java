package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    private LocalDate date;
    @ManyToOne @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeekerProfile jobSeeker;
    private Double amount;
}
