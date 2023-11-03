package fr.brenard.blogs.services;

import fr.brenard.blogs.models.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAll();


}
