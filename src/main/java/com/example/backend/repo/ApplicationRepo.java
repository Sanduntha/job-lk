package com.example.backend.repo;

import com.example.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    List<Application> findByJobSeekerId(Integer id);
    List<Application> findByJobId(int id);
}
