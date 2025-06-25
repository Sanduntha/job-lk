package com.example.backend.repo;

import com.example.backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByCourseId(Integer courseId);
    List<Enrollment> findByJobSeekerId(Integer jobSeekerId);
}
