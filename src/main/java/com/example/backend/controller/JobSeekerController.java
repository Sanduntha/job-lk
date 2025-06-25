package com.example.backend.controller;

import com.example.backend.dto.JobSeeker;
import com.example.backend.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobseeker")
@CrossOrigin("*")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    // Create a new Job Seeker
    @PostMapping
    public ResponseEntity<JobSeeker> createJobSeeker(@RequestBody JobSeeker jobSeeker) {
        JobSeeker created = jobSeekerService.createJobSeeker(jobSeeker);
        return ResponseEntity.ok(created);
    }

    // Get all Job Seekers
    @GetMapping
    public ResponseEntity<List<JobSeeker>> getAllJobSeekers() {
        List<JobSeeker> seekers = jobSeekerService.getAllJobSeekers();
        return ResponseEntity.ok(seekers);
    }

    // Get Job Seeker by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getJobSeekerById(@PathVariable Integer id) {
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(id);
        if (jobSeeker != null) {
            return ResponseEntity.ok(jobSeeker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Job Seeker by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSeeker(@PathVariable Integer id) {
        jobSeekerService.deleteJobSeeker(id);
        return ResponseEntity.noContent().build();
    }
}
