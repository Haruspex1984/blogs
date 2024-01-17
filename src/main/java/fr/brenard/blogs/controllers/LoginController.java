package fr.brenard.blogs.controllers;

import fr.brenard.blogs.exceptions.InvalidCredentialsException;
import fr.brenard.blogs.models.DTOs.AuthDTO;
import fr.brenard.blogs.models.forms.users.LoginForm;
import fr.brenard.blogs.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody @Valid LoginForm form) throws InvalidCredentialsException {
        return loginService.login(form);
    }



}
