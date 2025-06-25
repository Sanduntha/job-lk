package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    private Integer id;
    private Integer courseId;
    private Integer jobSeekerId;
    private LocalDate date;
    private Double amount;
}
