package com.example.backend.service.Impl;

import com.example.backend.dto.EnrollmentDto;
import com.example.backend.entity.Course;
import com.example.backend.entity.Enrollment;
import com.example.backend.entity.JobSeekerProfile;
import com.example.backend.repo.CourseRepo;
import com.example.backend.repo.EnrollmentRepo;
import com.example.backend.repo.JobSeekerProfileRepo;
import com.example.backend.service.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;
    private final CourseRepo courseRepo;
    private final JobSeekerProfileRepo jobSeekerProfileRepo;
    private final ModelMapper modelMapper;

    @Override
    public EnrollmentDto enroll(EnrollmentDto dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        JobSeekerProfile jobSeeker = jobSeekerProfileRepo.findById(dto.getJobSeekerId())
                .orElseThrow(() -> new EntityNotFoundException("Job seeker not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setJobSeeker(jobSeeker);
        enrollment.setAmount(dto.getAmount());
        enrollment.setDate(dto.getDate());

        return modelMapper.map(enrollmentRepo.save(enrollment), EnrollmentDto.class);
    }

    @Override
    public List<EnrollmentDto> getByCourse(Integer courseId) {
        return enrollmentRepo.findByCourseId(courseId).stream()
                .map(e -> modelMapper.map(e, EnrollmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDto> getByJobSeeker(Integer jobSeekerId) {
        return enrollmentRepo.findByJobSeekerId(jobSeekerId).stream()
                .map(e -> modelMapper.map(e, EnrollmentDto.class))
                .collect(Collectors.toList());
    }
}
