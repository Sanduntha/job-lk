package com.example.backend.repo;

import com.example.backend.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepo extends JpaRepository<Employer, Integer> {

}
