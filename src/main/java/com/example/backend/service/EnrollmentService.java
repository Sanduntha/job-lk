package com.example.backend.service;

import com.example.backend.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto enroll(EnrollmentDto dto);
    List<EnrollmentDto> getByCourse(Integer courseId);
    List<EnrollmentDto> getByJobSeeker(Integer jobSeekerId);
}
