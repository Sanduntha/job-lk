package com.example.backend.service.Impl;

import com.example.backend.dto.ApplicationDto;
import com.example.backend.entity.Application;
import com.example.backend.entity.Job;
import com.example.backend.entity.JobSeekerProfile;
import com.example.backend.repo.ApplicationRepo;
import com.example.backend.repo.JobRepo;
import com.example.backend.repo.JobSeekerProfileRepo;
import com.example.backend.service.ApplicationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepo applicationRepository;
    private final JobRepo jobRepository;
    private final JobSeekerProfileRepo jobSeekerProfileRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApplicationDto applyForJob(ApplicationDto dto) {
        // Validate required fields
        if (dto.getJobId() == null || dto.getJobSeekerId() == null) {
            throw new IllegalArgumentException("Job ID and Job Seeker ID must not be null");
        }

        if (!StringUtils.hasText(dto.getCv())) {
            throw new IllegalArgumentException("CV must not be empty");
        }

        // Fetch related entities
        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + dto.getJobId()));

        JobSeekerProfile jobSeeker = jobSeekerProfileRepository.findById(dto.getJobSeekerId())
                .orElseThrow(() -> new EntityNotFoundException("Job seeker not found with ID: " + dto.getJobSeekerId()));

        // Map DTO to entity
        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setCv(dto.getCv());
        application.setIntroduction(dto.getIntroduction());

        // Save and map back to DTO
        Application saved = applicationRepository.save(application);
        return modelMapper.map(saved, ApplicationDto.class);
    }

    @Override
    public List<ApplicationDto> getApplicationsByJobId(Integer jobId) {
        List<Application> applications = applicationRepository.findByJobId(jobId);
        return applications.stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getApplicationsByJobSeekerId(Integer jobSeekerId) {
        List<Application> applications = applicationRepository.findByJobSeekerId(jobSeekerId);
        return applications.stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(Collectors.toList());
    }
}
