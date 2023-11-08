package com.example.webchick.web;

import com.example.webchick.models.Brand;
import com.example.webchick.services.BrandService;
import com.example.webchick.services.dtos.BrandDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private  BrandService brandService;
    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String viewAllBrands(Model model){
        List<BrandDto> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        return "allBrands";
    }

    @GetMapping("/find/{id}")
    public String findBrand(Model model, @PathVariable("id") UUID uuid){
        Optional<BrandDto> dbBrand = brandService.findBrand(uuid);
        if (dbBrand.isPresent()) {
            BrandDto brand = dbBrand.get();
            model.addAttribute("brand", brand);
            return "findBrand";
        } else {
            return "brandNotFound";
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") UUID uuid){
        brandService.delete(uuid);
        return "redirect:/brand/all";
    }

    @GetMapping("/create")
    public String addNewBrand(Model model){
        model.addAttribute("brandDto", new BrandDto());
        return "addNewBrand";
    }

    @PostMapping("/create")
    public String addNewBrand(@ModelAttribute("brandDto") BrandDto brandDto){
        brandService.add(brandDto);
        return "redirect:/brand/all";
    }

    @GetMapping("/change/{id}")
    public String changeBrand(Model model, @PathVariable("id") UUID uuid){
        Optional<BrandDto> dbBrand = brandService.findBrand(uuid);
        if (dbBrand.isPresent()) {
            BrandDto brand = dbBrand.get();
            model.addAttribute("brandDto", brand);
            return "editBrand";
        } else {
            return "brandNotFound";
        }
    }
    @PostMapping("/change/{id}")
    public String saveChangeBrand(@PathVariable("id") UUID uuid, @ModelAttribute("brandDto") BrandDto brandDto) {
        Optional<Brand> dbBrand = brandService.findBrand(uuid);
        if (dbBrand.isPresent()) {
            brandService.update(brandDto);
            return "redirect:/brand/all";
        } else {
            return "brandNotFound";
        }
    }
}
