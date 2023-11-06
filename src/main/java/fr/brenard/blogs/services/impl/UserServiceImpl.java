package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.DTOs.UserDTO;

import fr.brenard.blogs.models.mappers.UserMapper;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserMapper::fromEntity).toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::fromEntity).orElseThrow(EntityNotFoundException::new);
    }
}
