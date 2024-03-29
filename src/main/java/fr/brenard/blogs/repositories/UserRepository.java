package fr.brenard.blogs.repositories;

import fr.brenard.blogs.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);


}
