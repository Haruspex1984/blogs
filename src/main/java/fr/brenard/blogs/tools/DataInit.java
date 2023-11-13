package fr.brenard.blogs.tools;

import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit implements InitializingBean {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DataInit(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;

        this.encoder = encoder;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        roles.add("USER");

        User user = new User();
        user.setUsername("Haruspex");
        user.setPassword(encoder.encode("Ambroise310877"));
        user.setEmailAddress("b.renard84@gmail.com");
        user.setRegisterDate(LocalDate.now());
        user.setRoles(roles);
        user = userRepository.save(user);

    }
}
