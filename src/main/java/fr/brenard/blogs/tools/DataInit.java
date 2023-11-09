package fr.brenard.blogs.tools;


import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInit implements InitializingBean {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final PasswordEncoder encoder;

    public DataInit(UserRepository userRepository, BlogRepository blogRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.encoder = encoder;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

//        Blog blog = new Blog();
//        blog.setTitle("Le blog de Benjamin");
//        blog.setCreationDate(LocalDate.now());

        User user = new User();
        user.setUsername("Haruspex");
        user.setPassword(encoder.encode("Ambroise310877"));
        user.setEmailAddress("b.renard84@gmail.com");
        user.setRegisterDate(LocalDate.now());

        user = userRepository.save(user);
//        blog.setUser(user);
//        blog = blogRepository.save(blog);
//        user.setBlog(blog);
        userRepository.save(user);















    }
}
