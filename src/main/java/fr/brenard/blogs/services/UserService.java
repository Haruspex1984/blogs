package fr.brenard.blogs.services;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.models.entities.User;


import java.util.List;

public interface UserService {

    List<UserDTO> getAll();
    UserDTO getUserDTOByUserId(Long id);


}
