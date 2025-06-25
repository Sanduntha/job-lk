package com.example.backend.controller;

import com.example.backend.dto.CourseDto;
import com.example.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers/{trainerId}/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public CourseDto add(@PathVariable Integer trainerId, @RequestBody CourseDto dto) {
        return courseService.addCourse(trainerId, dto);
    }

    @PutMapping("/{courseId}")
    public CourseDto update(@PathVariable Integer trainerId, @PathVariable Integer courseId, @RequestBody CourseDto dto) {
        return courseService.updateCourse(trainerId, courseId, dto);
    }

    @DeleteMapping("/{courseId}")
    public void delete(@PathVariable Integer trainerId, @PathVariable Integer courseId) {
        courseService.deleteCourse(trainerId, courseId);
    }

    @GetMapping("/view")
    public List<CourseDto> getByTrainer(@PathVariable Integer trainerId) {
        return courseService.getCoursesByTrainer(trainerId);
    }
}