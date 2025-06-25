package com.example.backend.service.Impl;

import com.example.backend.dto.JobSeeker;
import com.example.backend.entity.JobSeekerProfile;
import com.example.backend.entity.User;
import com.example.backend.repo.JobSeekerProfileRepo;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.JobSeekerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {

    private final JobSeekerProfileRepo jobSeekerProfileRepo;
    private final UserRepo userRepo;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerProfileRepo jobSeekerProfileRepo, UserRepo userRepo) {
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public JobSeeker createJobSeeker(JobSeeker dto) {
        // Create and save User
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        User savedUser = userRepo.save(user);

        // Create and save JobSeekerProfile
        JobSeekerProfile profile = new JobSeekerProfile();
        profile.setUser(savedUser);
        profile.setSkills(dto.getSkill());
        JobSeekerProfile savedProfile = jobSeekerProfileRepo.save(profile);

        // Return the result
        return mapToDto(savedUser, savedProfile);
    }

    @Override
    public JobSeeker getJobSeekerById(Integer id) {

        return null;
    }

    @Override
    public List<JobSeeker> getAllJobSeekers() {
        return null;
    }

    @Override
    public void deleteJobSeeker(Integer id) {
        jobSeekerProfileRepo.deleteById(id);
    }

    private JobSeeker mapToDto(User user, JobSeekerProfile profile) {
        JobSeeker dto = new JobSeeker();
        dto.setId(profile.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setSkill(profile.getSkills());
        return dto;
    }
}
