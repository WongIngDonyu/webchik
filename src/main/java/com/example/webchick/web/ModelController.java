package com.example.webchick.web;

import com.example.webchick.services.ModelService;
import com.example.webchick.services.dtos.ModelDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;
    public ModelController(ModelService modelService, ModelMapper modelMapper) {
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String viewAllModels(Model model){
        model.addAttribute(modelService.getAll());
        return "allModels";
    }

    @GetMapping("/find/{id}")
    public String findModel(Model model, @PathVariable("id") UUID uuid){
        model.addAttribute(modelService.findModel(uuid));
        return "findModel";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteModel(@PathVariable("id") UUID uuid){
        modelService.delete(uuid);
        return "redirect:/model/all";
    }

    @GetMapping("/create")
    public String addNewModel(){
        return "addNewModel";
    }

    @PostMapping("/create")
    public String addNewModel(@RequestBody ModelDto modelDto){
        modelService.add(modelDto);
        return "redirect:/model/all";
    }
    @GetMapping("/change/{id}")
    public String changeModel(Model model, @PathVariable("id") UUID uuid){
        Optional<Model> dbModel = modelService.findModel(uuid);
        if (dbModel.isPresent()) {
            ModelDto modelDto = modelMapper.map(dbModel.get(), ModelDto.class);
            model.addAttribute("model", modelDto);
            return "editModel";
        } else {
            return "modelNotFound";
        }
    }
    @PostMapping("/change/{id}")
    public String saveChangeModel(@PathVariable("id") UUID uuid, @RequestBody ModelDto modelDto) {
        Optional<Model> dbModel = modelService.findModel(uuid);
        if (dbModel.isPresent()) {
            modelService.update(modelDto);
            return "redirect:/model/all";
        } else {
            return "modelNotFound";
        }
    }
}
