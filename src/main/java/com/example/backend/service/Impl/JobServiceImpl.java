package com.example.backend.service.Impl;

import com.example.backend.dto.JobDto;
import com.example.backend.entity.Job;
import com.example.backend.repo.JobRepo;
import com.example.backend.service.JobService;

import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {

    private JobRepo jobRepo;

    public JobDto postJob(JobDto jobDto) {
        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());
        jobRepo.save(job);
        return jobDto;
    }

    @Override
    public JobDto getJob(String jobId) {
        return null;
    }


    public List<JobDto> listJobs() {
        return jobRepo.findAll().stream()
                .map(job -> new JobDto(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getLocation(),
                        job.getSalary()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public JobDto deleteJob(Integer id) {
        int jobId = Integer.parseInt(String.valueOf(id));
        jobRepo.deleteById(id);
        return null;
    }

}

