package com.example.webchick.services.dtos;

import com.example.webchick.utils.validation.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;

public class AddBrandDto {
    private String name;

    @NotEmpty(message = "Brand name must not be empty!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
