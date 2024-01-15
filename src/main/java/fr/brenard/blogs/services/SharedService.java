package fr.brenard.blogs.services;

import fr.brenard.blogs.models.entities.Article;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;

public interface SharedService {
    void saveBlog(Blog blog);
    void saveUser(User user);
    void saveArticle(Article article);
    User getUserById(Long id);
    Blog getBlogById(Long id);
    Article getArticleById(Long id);

}
