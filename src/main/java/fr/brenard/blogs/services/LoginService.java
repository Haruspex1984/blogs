package fr.brenard.blogs.services;

import fr.brenard.blogs.models.DTOs.AuthDTO;
import fr.brenard.blogs.models.forms.users.LoginForm;

public interface LoginService {

    AuthDTO login(LoginForm form);
}
