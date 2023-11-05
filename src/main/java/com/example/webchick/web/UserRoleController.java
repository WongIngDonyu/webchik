package com.example.webchick.web;

import com.example.webchick.models.User;
import com.example.webchick.models.UserRole;
import com.example.webchick.services.UserRoleService;
import com.example.webchick.services.dtos.UserRoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
@Controller
@RequestMapping("/role")
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    public UserRoleController(UserRoleService userRoleService, ModelMapper modelMapper) {
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String viewAllUserRoles(Model model){
        model.addAttribute(userRoleService.getAll());
        return "allUserRoles";
    }

    @GetMapping("/find/{id}")
    public String findUserRole(Model model, @PathVariable("id") UUID uuid){
        model.addAttribute(userRoleService.findUserRole(uuid));
        return "findUserRole";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserRole(@PathVariable("id") UUID uuid){
        userRoleService.delete(uuid);
        return "redirect:/role/all";
    }

    @GetMapping("/create")
    public String addNewUserRole(){
        return "addNewRole";
    }

    @PostMapping("/create")
    public String addNewUserRole(@RequestBody UserRoleDto userRoleDto){
        userRoleService.add(userRoleDto);
        return "redirect:/role/all";
    }
    @GetMapping("/change/{id}")
    public String changeUserRole(Model model, @PathVariable("id") UUID uuid){
        Optional<UserRole> dbUserRole = userRoleService.findUserRole(uuid);
        if (dbUserRole.isPresent()) {
            UserRoleDto userRoleDto = modelMapper.map(dbUserRole.get(), UserRoleDto.class);
            model.addAttribute("model", userRoleDto);
            return "editUserRole";
        } else {
            return "roleNotFound";
        }
    }
    @PostMapping("/change/{id}")
    public String saveChangeUserRole(@PathVariable("id") UUID uuid, @RequestBody UserRoleDto userRoleDto) {
        Optional<User> dbUserRole = userRoleService.findUserRole(uuid);
        if (dbUserRole.isPresent()) {
            userRoleService.update(userRoleDto);
            return "redirect:/role/all";
        } else {
            return "roleNotFound";
        }
    }
}
