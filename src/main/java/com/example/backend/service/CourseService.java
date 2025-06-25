package com.example.backend.service;

import com.example.backend.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto addCourse(Integer trainerId, CourseDto dto);
    CourseDto updateCourse(Integer trainerId, Integer courseId, CourseDto dto);
    void deleteCourse(Integer trainerId, Integer courseId);
    List<CourseDto> getCoursesByTrainer(Integer trainerId);
}
