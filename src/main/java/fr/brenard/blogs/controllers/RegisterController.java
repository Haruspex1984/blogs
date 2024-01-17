package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.models.forms.users.RegisterForm;
import fr.brenard.blogs.services.RegisterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {


    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/newUser")
    private UserDTO register(@RequestBody @Valid RegisterForm form) {
        return registerService.registerNewUser(form);
    }


}
