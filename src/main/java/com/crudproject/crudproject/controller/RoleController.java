/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudproject.crudproject.controller;

import com.crudproject.crudproject.entity.Role;
import com.crudproject.crudproject.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dimitri
 */
@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @GetMapping
    public String findAllRoles(Model model) {
        List<Role> roles =  (List<Role>) this.roleRepository.findAll();
        model.addAttribute("roles",roles);
        return "pages/role/index";
        
    }

    @GetMapping("/create")
    public String addOneRole(Model model) {
        model.addAttribute("role", new Role());
        return "pages/role/create";
    }

    @PostMapping("/create")
    public String saveRole(@ModelAttribute Role role, Model model) {
        Role recupNom = roleRepository.findByName(role.getName());
        if (recupNom.getName() == null) {
            roleRepository.save(role);

        }else{
            model.addAttribute("message", role.getName());
            return "pages/role/create";
        }
        
        System.out.println(recupNom.getName());
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            model.addAttribute("role", optionalRole.get());
            return "pages/role/edit";
        } else {
            return "redirect:/roles";
        }
    }

    @PostMapping("/edit/{role_id}")
    public String updateRole(@PathVariable Long role_id, @ModelAttribute Role role) {
        roleRepository.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }

}
