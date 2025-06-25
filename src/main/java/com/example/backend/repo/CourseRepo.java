package com.example.backend.repo;

import com.example.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByTrainerId(Integer trainerId);
}