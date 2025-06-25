package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Integer id;
    private String title;
    private Integer duration;
    private Double fee;
    private String description;
    private Integer trainerId;
}
