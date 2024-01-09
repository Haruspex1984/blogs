package fr.brenard.blogs.services;

import fr.brenard.blogs.models.entities.Article;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;

public interface SharedService {
    void saveBlog(Blog blog);
    void saveUser(User user);
    void saveArticle(Article article);
    User findUserById(Long id);
    Blog findBlogById(Long id);

}
