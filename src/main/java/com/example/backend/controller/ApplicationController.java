package com.example.backend.controller;

import com.example.backend.dto.ApplicationDto;
import com.example.backend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // Apply for a job
    @PostMapping
    public ResponseEntity<ApplicationDto> applyForJob(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto savedApplication = applicationService.applyForJob(applicationDto);
        return ResponseEntity.ok(savedApplication);
    }

    // Get all applications for a specific job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByJob(@PathVariable Integer jobId) {
        List<ApplicationDto> applications = applicationService.getApplicationsByJobId(jobId);
        return ResponseEntity.ok(applications);
    }

    // Get all applications for a specific job seeker
    @GetMapping("/jobseeker/{jobSeekerId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByJobSeeker(@PathVariable Integer jobSeekerId) {
        List<ApplicationDto> applications = applicationService.getApplicationsByJobSeekerId(jobSeekerId);
        return ResponseEntity.ok(applications);
    }
}
