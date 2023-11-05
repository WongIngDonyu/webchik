package com.example.webchick.web;

import com.example.webchick.models.Offer;
import com.example.webchick.services.OfferService;
import com.example.webchick.services.dtos.OfferDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String viewAllOffers(Model model){
        model.addAttribute(offerService.getAll());
        return "allOffers";
    }

    @GetMapping("/find/{id}")
    public String findOffer(Model model, @PathVariable("id") UUID uuid){
        model.addAttribute(offerService.findOffer(uuid));
        return "findOffer";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID uuid){
        offerService.delete(uuid);
        return "redirect:/offer/all";
    }

    @GetMapping("/create")
    public String addNewOffer(){
        return "addNewOffer";
    }

    @PostMapping("/create")
    public String addNewOffer(@RequestBody OfferDto offerDto){
        offerService.add(offerDto);
        return "redirect:/offer/all";
    }
    @GetMapping("/change/{id}")
    public String changeOffer(Model model, @PathVariable("id") UUID uuid){
        Optional<Offer> dbOffer = offerService.findOffer(uuid);
        if (dbOffer.isPresent()) {
            OfferDto offerDto = modelMapper.map(dbOffer.get(), OfferDto.class);
            model.addAttribute("model", offerDto);
            return "editOffer";
        } else {
            return "offerNotFound";
        }
    }
    @PostMapping("/change/{id}")
    public String saveChangeOffer(@PathVariable("id") UUID uuid, @RequestBody OfferDto offerDto) {
        Optional<Offer> dbOffer = offerService.findOffer(uuid);
        if (dbOffer.isPresent()) {
            offerService.update(offerDto);
            return "redirect:/offer/all";
        } else {
            return "offerNotFound";
        }
    }
}
