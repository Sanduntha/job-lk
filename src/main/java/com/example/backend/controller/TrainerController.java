package com.example.backend.controller;

import com.example.backend.dto.TrainerDto;
import com.example.backend.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping("/add")
    public TrainerDto create(@RequestBody TrainerDto dto) {
        return trainerService.addTrainer(dto);
    }

    @PutMapping("/{id}")
    public TrainerDto update(@PathVariable Integer id, @RequestBody TrainerDto dto) {
        return trainerService.updateTrainer(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        trainerService.deleteTrainer(id);
    }

    @GetMapping("/view")
    public List<TrainerDto> list() {
        return trainerService.getAllTrainers();
    }
}