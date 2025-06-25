package com.example.backend.controller;

import com.example.backend.dto.EmployerDto;
import com.example.backend.dto.JobDto;
import com.example.backend.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping("/{employerId}/jobs")
    public ResponseEntity<JobDto> addJob(@PathVariable Integer employerId, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(employerService.addJob(employerId, jobDto));
    }

    @PutMapping("/{employerId}/jobs/{jobId}")
    public ResponseEntity<JobDto> updateJob(
            @PathVariable Integer employerId,
            @PathVariable Integer jobId,
            @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(employerService.updateJob(employerId, jobId, jobDto));
    }

    @DeleteMapping("/{employerId}/jobs/{jobId}")
    public ResponseEntity<Void> deleteJob(
            @PathVariable Integer employerId,
            @PathVariable Integer jobId) {
        employerService.deleteJob(employerId, jobId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{employerId}/jobs")
    public ResponseEntity<List<JobDto>> getAllJobs(@PathVariable Integer employerId) {
        return ResponseEntity.ok(employerService.getAllJobs(employerId));
    }
}

