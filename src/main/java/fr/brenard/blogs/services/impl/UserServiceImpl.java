package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
