package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String jobTitle;  // Added jobTitle to match DTO

    private String skills;

    private String experience;

    private String phone;  // Add phone if you want to store here alternatively get from User entity

//    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
//    private Set<Resume> resumes = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Application> applications;
}
