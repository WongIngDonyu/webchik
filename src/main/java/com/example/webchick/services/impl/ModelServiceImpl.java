package com.example.webchick.services.impl;

import com.example.webchick.models.Brand;
import com.example.webchick.models.Model;
import com.example.webchick.repositories.BrandRepository;
import com.example.webchick.repositories.ModelRepository;
import com.example.webchick.services.ModelService;
import com.example.webchick.services.dtos.ModelDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl  implements ModelService<UUID> {
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final BrandServiceImpl brandService;

    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, BrandRepository brandRepository, BrandServiceImpl brandService) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }


    @Override
    public void delete(ModelDto model) {
        modelRepository.deleteById(model.getId());
    }

    @Override
    public void delete(UUID id) {
        modelRepository.deleteById(id);
    }

    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map((m)->modelMapper.map(m, ModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ModelDto> findModel(UUID id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), ModelDto.class));
    }

    @Override
    public ModelDto add(ModelDto model) {
        Model m = modelMapper.map(model, Model.class);
        m.setBrand(brandService.findBrandByName(model.getBrandName()));
        m.setCreated(LocalDateTime.now());
        return modelMapper.map(modelRepository.saveAndFlush(m), ModelDto.class);
    }

    @Override
    public ModelDto update(ModelDto modelDto) {
        Optional<Model> dbModel = modelRepository.findById(modelDto.getId());
        if (dbModel.isEmpty()) {
            throw new NoSuchElementException("Model not found");
        }
            Model model = dbModel.get();
            modelMapper.map(modelDto, model);
            Optional<Brand> dbBrand = brandRepository.findByName(modelDto.getBrandName());
            if (dbBrand.isEmpty()){
                throw new NoSuchElementException("Brand not found");
            }
            model.setBrand(brandService.findBrandByName(modelDto.getBrandName()));
            model.setModified(LocalDateTime.now());
            model.setCreated(dbModel.get().getCreated());
            return modelMapper.map(modelRepository.saveAndFlush(model), ModelDto.class);
        }

    @Override
    public Model findByName(String name) {
        return modelRepository.findByName(name).orElse(null);
    }
}

