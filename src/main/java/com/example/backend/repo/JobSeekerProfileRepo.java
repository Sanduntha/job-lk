package com.example.backend.repo;

import com.example.backend.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobSeekerProfileRepo extends JpaRepository<JobSeekerProfile, Integer> {

    Optional<JobSeekerProfile> findById(Integer jobSeekerId);

    void deleteById(Integer id);

    JobSeekerProfile save(JobSeekerProfile profile);

    List<JobSeekerProfile> findAll();
}
