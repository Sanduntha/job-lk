package com.example.backend.controller;

import com.example.backend.dto.EnrollmentDto;
import com.example.backend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public EnrollmentDto enroll(@RequestBody EnrollmentDto dto) {
        return enrollmentService.enroll(dto);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentDto> getByCourse(@PathVariable Integer courseId) {
        return enrollmentService.getByCourse(courseId);
    }

    @GetMapping("/jobseeker/{jobSeekerId}")
    public List<EnrollmentDto> getByJobSeeker(@PathVariable Integer jobSeekerId) {
        return enrollmentService.getByJobSeeker(jobSeekerId);
    }
}