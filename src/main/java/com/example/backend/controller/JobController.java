package com.example.backend.controller;

import com.example.backend.dto.JobDto;
import com.example.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private JobService jobService;

    @PostMapping("/post")
    public ResponseEntity<JobDto> postJob(@RequestBody JobDto jobDto) {
        JobDto savedJob = jobService.postJob(jobDto);
        return ResponseEntity.ok(savedJob);
    }

    @GetMapping("/list")
    public ResponseEntity<List<JobDto>> listJobs() {
        List<JobDto> jobs = jobService.listJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/update")
    public ResponseEntity<JobDto> updateJob(@RequestBody JobDto jobDto) {
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job Deleted Successfully");
    }
}