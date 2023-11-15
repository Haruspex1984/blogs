package fr.brenard.blogs.repositories;

import fr.brenard.blogs.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
