package com.example.backend.service;

import com.example.backend.dto.JobSeeker;

import java.util.List;

public interface JobSeekerService {
    JobSeeker createJobSeeker(JobSeeker dto);
    JobSeeker getJobSeekerById(Integer id);
    List<JobSeeker> getAllJobSeekers();
    void deleteJobSeeker(Integer id);

}
