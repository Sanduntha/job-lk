package com.example.backend.service.Impl;

import com.example.backend.dto.JobDto;
import com.example.backend.entity.Job;
import com.example.backend.repo.JobRepo;
import com.example.backend.service.JobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;
    private final ModelMapper modelMapper;

    @Override
    public JobDto postJob(JobDto jobDto) {
        // Map DTO to entity
        Job job = modelMapper.map(jobDto, Job.class);

        // Save job entity
        Job savedJob = jobRepo.save(job);

        // Map saved entity back to DTO and return (with ID)
        return modelMapper.map(savedJob, JobDto.class);
    }

    @Override
    public JobDto updateJob(String jobId) {
        Optional<Job> jobOptional = jobRepo.findById(Integer.parseInt(jobId));
        if (jobOptional.isPresent()) {
            return modelMapper.map(jobOptional.get(), JobDto.class);
        }
        return null;  // or throw an exception if preferred
    }

    @Override
    public List<JobDto> listJobs() {
        return jobRepo.findAll()
                .stream()
                .map(job -> modelMapper.map(job, JobDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobDto deleteJob(Integer id) {
        Optional<Job> jobOptional = jobRepo.findById(id);
        if (jobOptional.isPresent()) {
            jobRepo.deleteById(id);
            return modelMapper.map(jobOptional.get(), JobDto.class);
        }
        return null;  // or throw exception if job not found
    }

}
