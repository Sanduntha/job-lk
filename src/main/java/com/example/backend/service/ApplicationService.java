package com.example.backend.service;

import com.example.backend.dto.ApplicationDto;
import java.util.List;

public interface ApplicationService {
    ApplicationDto applyForJob(ApplicationDto dto);
    List<ApplicationDto> getApplicationsByJobId(Integer jobId);
    List<ApplicationDto> getApplicationsByJobSeekerId(Integer jobSeekerId);
}
