package com.example.webchick.services.impl;

import com.example.webchick.models.Brand;
import com.example.webchick.repositories.BrandRepository;
import com.example.webchick.services.BrandService;
import com.example.webchick.services.dtos.BrandDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService<UUID> {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public void delete(BrandDto brand) {
        brandRepository.deleteById(brand.getId());
    }

    @Override
    public void delete(UUID id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map((b) -> modelMapper.map(b, BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDto> findBrand(UUID id) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(id), BrandDto.class));
    }

    @Override
    public BrandDto add(BrandDto brand) {
        Brand b = modelMapper.map(brand, Brand.class);
        b.setCreated(LocalDateTime.now());
        return modelMapper.map(brandRepository.save(b), BrandDto.class);
    }

    @Override
    public BrandDto update(BrandDto brandDto) {
        Optional<Brand> dbBrand = brandRepository.findById(brandDto.getId());
        if(dbBrand.isEmpty()){
            throw new NoSuchElementException("Brand not found");
        }
        else {
            Brand brand1 = modelMapper.map(brandDto, Brand.class);
            brand1.setCreated(dbBrand.get().getCreated());
            brand1.setModified(LocalDateTime.now());
            return modelMapper.map(brandRepository.save(brand1), BrandDto.class);
        }
    }

    @Override
    public Brand findBrandByName(String brandName) {
        return brandRepository.findByName(brandName).orElse(null);
    }
}
