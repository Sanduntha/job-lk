package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private Integer id;
    private Integer jobId;
    private Integer jobSeekerId;
    private String cv;
    private String introduction;
}
