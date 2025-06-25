package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private Integer id;
    private String name;
    private String address;
    private String contactNumber;
    private List<JobDto> jobs;
}
