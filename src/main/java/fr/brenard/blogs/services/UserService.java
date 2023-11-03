package fr.brenard.blogs.services;

import fr.brenard.blogs.models.DTOs.UserDTO;


import java.util.List;

public interface UserService {

    List<UserDTO> getAll();


}
