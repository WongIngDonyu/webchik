package com.example.webchick.init;

import com.example.webchick.models.Model;
import com.example.webchick.models.Offer;
import com.example.webchick.models.UserRole;
import com.example.webchick.services.*;
import com.example.webchick.services.dtos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandService brandService;

    private final ModelService modelService;

    private final UserRoleService userRoleService;

    private final UserService userService;

    private final OfferService offerService;

    public DataInitializer(BrandService brandService, ModelService modelService, UserRoleService userRoleService, UserService userService, OfferService offerService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {
        BrandDto b1 = new BrandDto(UUID.randomUUID(),"Zoo");
        BrandDto sb1 = brandService.add(b1);
        BrandDto b2 = new BrandDto(UUID.randomUUID(),"ZOV");
        BrandDto sb2 = brandService.add(b2);
        sb1.setName("LOX");
        brandService.update(sb1);
        ModelDto m1 = new ModelDto(UUID.randomUUID(),"Zoo1", Model.Category.Motorcycle, "SomeUrl", 2023, 2054,"Zoo");
        ModelDto modelDto = modelService.add(m1);
        modelDto.setName("alallala");
        modelDto.setBrand("ZOV");
        modelService.update(modelDto);
        UserRoleDto userRoleDto = new UserRoleDto(UUID.randomUUID(), UserRole.Role.User);
        UserRoleDto userRoleDto1 = userRoleService.add(userRoleDto);
        userRoleDto1.setRole(UserRole.Role.Admin);
        userRoleService.update(userRoleDto1);
        UserDto userDto = new UserDto(UUID.randomUUID(), "1","1488","Egor", "Ananasiy", true, "someUrl", UserRole.Role.Admin);
        UserDto userDro1 = userService.add(userDto);
        userDro1.setActive(false);
        userService.update(userDro1);
        UserRoleDto userRoleDto2 = new UserRoleDto(UUID.randomUUID(), UserRole.Role.User);
        UserRoleDto userRoleDto3 = userRoleService.add(userRoleDto2);
        userDro1.setRole(UserRole.Role.User);
        userService.update(userDro1);
        OfferDto offerDto = new OfferDto(UUID.randomUUID(),"somekool", Offer.Engine.DIESEL, "someUrl", 100, 100000, Offer.Transmission.AUTOMATIC, 2003,"1", "alallala");
        OfferDto offerDto1 = offerService.add(offerDto);
        System.out.println(userService.findByUsername(offerDto1.getUsername()));
        offerDto1.setPrice(6666);
        offerService.update(offerDto1);
    }
}