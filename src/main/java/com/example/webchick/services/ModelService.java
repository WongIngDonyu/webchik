package com.example.webchick.services;

import com.example.webchick.models.Model;
import com.example.webchick.services.dtos.ModelDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService <I extends UUID>{
    void delete(ModelDto model);

    void delete(UUID id);

    List<ModelDto> getAll();

    Optional<ModelDto> findModel(UUID id);

    ModelDto add(ModelDto model);

    ModelDto update(ModelDto modelDt);
    Model findByName(String name);
}
