package com.example.webchick.web;

import com.example.webchick.models.User;
import com.example.webchick.services.UserService;
import com.example.webchick.services.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String viewAllUsers(Model model){
        model.addAttribute(userService.getAll());
        return "allUsers";
    }

    @GetMapping("/find/{id}")
    public String findUser(Model model, @PathVariable("id") UUID uuid){
        model.addAttribute(userService.findUser(uuid));
        return "findUser";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID uuid){
        userService.delete(uuid);
        return "redirect:/user/all";
    }

    @GetMapping("/create")
    public String addNewUser(){
        return "addNewUser";
    }

    @PostMapping("/create")
    public String addNewUser(@RequestBody UserDto userDto){
        userService.add(userDto);
        return "redirect:/user/all";
    }
    @GetMapping("/change/{id}")
    public String changeUser(Model model, @PathVariable("id") UUID uuid){
        Optional<User> dbUser = userService.findUser(uuid);
        if (dbUser.isPresent()) {
            UserDto userDto = modelMapper.map(dbUser.get(), UserDto.class);
            model.addAttribute("model", userDto);
            return "editUser";
        } else {
            return "userNotFound";
        }
    }
    @PostMapping("/change/{id}")
    public String saveChangeUser(@PathVariable("id") UUID uuid, @RequestBody UserDto userDto) {
        Optional<User> dbUser = userService.findUser(uuid);
        if (dbUser.isPresent()) {
            userService.update(userDto);
            return "redirect:/user/all";
        } else {
            return "userNotFound";
        }
    }
}
