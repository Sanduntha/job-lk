package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    private Integer id;
    private String name;
    private String courseCategory;
    private String contactNumber;
    private String qualification;
    private Integer experience;
    private List<CourseDto> courses;

}

