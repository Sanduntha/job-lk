package com.example.backend.service.Impl;

import com.example.backend.dto.EmployerDto;
import com.example.backend.dto.JobDto;
import com.example.backend.entity.Employer;
import com.example.backend.entity.Job;
import com.example.backend.repo.EmployerRepo;
import com.example.backend.repo.JobRepo;
import com.example.backend.service.EmployerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepo employerRepository;
    private final JobRepo jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public JobDto addJob(Integer employerId, JobDto jobDto) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found"));

        Job job = modelMapper.map(jobDto, Job.class);
        job.setEmployer(employer);
        Job savedJob = jobRepository.save(job);
        return modelMapper.map(savedJob, JobDto.class);
    }

    @Override
    public JobDto updateJob(Integer employerId, Integer jobId, JobDto jobDto) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        if (!job.getEmployer().getId().equals(employerId)) {
            throw new SecurityException("You can only update your own jobs");
        }

        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());

        Job updated = jobRepository.save(job);
        return modelMapper.map(updated, JobDto.class);
    }

    @Override
    public void deleteJob(Integer employerId, Integer jobId) {
        if (!jobRepository.existsByIdAndEmployerId(jobId, employerId)) {
            throw new SecurityException("You can only delete your own jobs");
        }
        jobRepository.deleteById(jobId);
    }

    @Override
    public List<JobDto> getAllJobs(Integer employerId) {
        List<Job> jobs = jobRepository.findByEmployerId(employerId);
        return jobs.stream()
                .map(job -> modelMapper.map(job, JobDto.class))
                .collect(Collectors.toList());
    }
}