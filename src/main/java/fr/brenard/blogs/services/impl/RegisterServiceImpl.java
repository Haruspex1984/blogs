package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.UsernameAlreadyExistsException;
import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.users.RegisterForm;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.RegisterService;
import fr.brenard.blogs.services.SharedService;
import fr.brenard.blogs.tools.mappers.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SharedService sharedService;



    public RegisterServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, SharedService sharedService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.sharedService = sharedService;
    }


    @Override
    public UserDTO registerNewUser(RegisterForm form) {

        if(usernameAlreadyExists(form)){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = createAndSetUpNewUser(form);
        assignUserRoleToUser(user);
        sharedService.saveUser(user);
        return UserMapper.fromEntity(user);
    }


    private boolean usernameAlreadyExists(RegisterForm form){
        return userRepository.existsByUsername(form.getUsername());
    }

    private User createAndSetUpNewUser(RegisterForm form){
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setEmailAddress(form.getEmailAddress());
        user.setRegisterDate(LocalDate.now());
        return user;
    }

    private void assignUserRoleToUser(User user){
        Set<String> roles = new HashSet<String>();
        roles.add("USER");
        user.setRoles(roles);
    }


}
