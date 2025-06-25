package com.example.backend.service;

import com.example.backend.dto.EmployerDto;
import com.example.backend.dto.JobDto;

import java.util.List;

public interface EmployerService {
        JobDto addJob(Integer employerId, JobDto jobDto);
        JobDto updateJob(Integer employerId, Integer jobId, JobDto jobDto);
        void deleteJob(Integer employerId, Integer jobId);
        List<JobDto> getAllJobs(Integer employerId);
    }

