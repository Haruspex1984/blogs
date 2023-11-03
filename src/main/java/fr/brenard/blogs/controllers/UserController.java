package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.services.UserService;
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


    @RequestMapping("/all")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }


}
