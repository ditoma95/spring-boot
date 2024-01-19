/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudproject.crudproject.controller;

import com.crudproject.crudproject.entity.Role;
import com.crudproject.crudproject.entity.User;
import com.crudproject.crudproject.repository.RoleRepository;
import com.crudproject.crudproject.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dimitri
 */
@Controller
@RequestMapping("/pages")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String findAllUsers(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "pages/user/listuser";
    }

    @GetMapping("/create")
    public String addOneUser(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = (List<Role>) this.roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "pages/user/createuser";
    }

    @PostMapping("/create")
    //
    public String saveUser(@RequestBody User user, @RequestParam(name = "roles", required = false) List<Long> roleIds) {
        List<Role> userRoles = roleIds.stream()
                .map(roleId -> roleRepository.findById(roleId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        user.setRoles(userRoles);
        userRepository.save(user);

        return "redirect:/pages";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "pages/user/edit";
        } else {
            return "redirect:/pages";
        }
    }

    @PostMapping("/edit/{user_id}")
    public String updateUser(@PathVariable Long user_id, @RequestBody User updatedUser) {
        userRepository.save(updatedUser);
        return "redirect:/pages";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/pages";
    }


    //Formulaire de login
    @GetMapping("/login")
    public String login(Model model) {
        return "pages/user/login";
    }

}