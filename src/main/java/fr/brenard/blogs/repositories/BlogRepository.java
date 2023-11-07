package fr.brenard.blogs.repositories;

import fr.brenard.blogs.models.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
