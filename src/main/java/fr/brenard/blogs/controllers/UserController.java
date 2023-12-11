package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserDTOByUserId(id);
    }




}
