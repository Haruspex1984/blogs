package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.UserNotFoundException;
import fr.brenard.blogs.models.entities.Article;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.repositories.ArticleRepository;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.SharedService;
import org.springframework.stereotype.Service;

@Service
public class SharedServiceImpl implements SharedService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public SharedServiceImpl(BlogRepository blogRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public Blog findBlogById(Long id) {
        User user = findUserById(id);
        try{
            return user.getBlog();
        } catch (UserNotFoundException e){
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}
