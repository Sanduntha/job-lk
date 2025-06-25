package com.example.backend.service.Impl;

import com.example.backend.dto.TrainerDto;
import com.example.backend.entity.Trainer;
import com.example.backend.repo.TrainerRepo;
import com.example.backend.service.TrainerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepo trainerRepo;
    private final ModelMapper modelMapper;

    @Override
    public TrainerDto addTrainer(TrainerDto dto) {
        Trainer trainer = modelMapper.map(dto, Trainer.class);
        return modelMapper.map(trainerRepo.save(trainer), TrainerDto.class);
    }

    @Override
    public TrainerDto updateTrainer(Integer id, TrainerDto dto) {
        Trainer trainer = trainerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        trainer.setName(dto.getName());
        trainer.setCourseCategory(dto.getCourseCategory());
        trainer.setContactNumber(dto.getContactNumber());
        trainer.setQualification(dto.getQualification());
        trainer.setExperience(dto.getExperience());
        return modelMapper.map(trainerRepo.save(trainer), TrainerDto.class);
    }

    @Override
    public void deleteTrainer(Integer id) {
        trainerRepo.deleteById(id);
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        return trainerRepo.findAll().stream()
                .map(trainer -> modelMapper.map(trainer, TrainerDto.class))
                .collect(Collectors.toList());
    }

}