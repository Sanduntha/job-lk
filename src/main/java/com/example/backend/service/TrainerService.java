package com.example.backend.service;

import com.example.backend.dto.TrainerDto;

import java.util.List;

public interface TrainerService {
    TrainerDto addTrainer(TrainerDto dto);
    TrainerDto updateTrainer(Integer id, TrainerDto dto);
    void deleteTrainer(Integer id);
    List<TrainerDto> getAllTrainers();
}
