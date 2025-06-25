package com.example.backend.service.Impl;

import com.example.backend.dto.CourseDto;
import com.example.backend.entity.Course;
import com.example.backend.entity.Trainer;
import com.example.backend.repo.CourseRepo;
import com.example.backend.repo.TrainerRepo;
import com.example.backend.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final TrainerRepo trainerRepo;
    private final ModelMapper modelMapper;

    @Override
    public CourseDto addCourse(Integer trainerId, CourseDto dto) {
        Trainer trainer = trainerRepo.findById(trainerId)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        Course course = modelMapper.map(dto, Course.class);
        course.setTrainer(trainer);
        return modelMapper.map(courseRepo.save(course), CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(Integer trainerId, Integer courseId, CourseDto dto) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (!course.getTrainer().getId().equals(trainerId)) {
            throw new SecurityException("Unauthorized to update this course");
        }
        course.setTitle(dto.getTitle());
        course.setDuration(dto.getDuration());
        course.setFee(dto.getFee());
        course.setDescription(dto.getDescription());
        return modelMapper.map(courseRepo.save(course), CourseDto.class);
    }

    @Override
    public void deleteCourse(Integer trainerId, Integer courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (!course.getTrainer().getId().equals(trainerId)) {
            throw new SecurityException("Unauthorized to delete this course");
        }
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> getCoursesByTrainer(Integer trainerId) {
        return courseRepo.findByTrainerId(trainerId).stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }
}
