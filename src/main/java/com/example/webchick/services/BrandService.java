package com.example.webchick.services;

import com.example.webchick.models.Brand;
import com.example.webchick.services.dtos.BrandDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandService <I extends UUID>{
    void delete(BrandDto brand);

    void delete(UUID id);

    List<BrandDto> getAll();

    Optional<BrandDto> findBrand(UUID id);

    BrandDto add(BrandDto brand);
    BrandDto update(BrandDto brand);
    Brand findBrandByName(String brandName);
}
