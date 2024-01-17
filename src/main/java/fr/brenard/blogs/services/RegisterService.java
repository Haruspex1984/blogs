package fr.brenard.blogs.services;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.models.forms.users.RegisterForm;

public interface RegisterService {

    UserDTO registerNewUser(RegisterForm form);

}
