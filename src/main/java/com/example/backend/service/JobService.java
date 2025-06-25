package com.example.backend.service;

import com.example.backend.dto.JobDto;

import java.util.List;

public interface JobService {

    JobDto postJob(JobDto jobDto);
    JobDto updateJob(String jobId);
    List<JobDto> listJobs();
    JobDto deleteJob(Integer jobId);
}
