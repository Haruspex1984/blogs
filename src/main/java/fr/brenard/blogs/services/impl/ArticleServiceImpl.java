package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.DTOs.ArticleDTO;
import fr.brenard.blogs.models.entities.Article;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.ArticleForm;
import fr.brenard.blogs.repositories.ArticleRepository;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.ArticleService;
import fr.brenard.blogs.tools.mappers.ArticleMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public List<ArticleDTO> getAll() {
        return articleRepository.findAll().stream().map(ArticleMapper::fromEntity).toList();
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        return articleRepository.findById(id).map(ArticleMapper::fromEntity).orElseThrow();
    }

    @Override
    public void createAndSetUpNewArticle(ArticleForm form, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Blog blog = user.getBlog();

        Article article = new Article();

        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setVisible(form.isVisible());
        article.setCreationDate(LocalDateTime.now());
        article.setUser(user);
        article.setBlog(blog);

        updateNumberOfArticles(blog,+1);

        blogRepository.save(blog);
        articleRepository.save(article);
    }

    private void updateNumberOfArticles(Blog blog, int amount){
        blog.setNumberOfArticles(blog.getNumberOfArticles()+amount);
    }


    @Override
    public void updateArticle(ArticleForm form, Long articleId) {

        Article article = getArticle(articleId);

        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setVisible(form.isVisible());
        article.setLastUpdateDate(LocalDateTime.now());

        articleRepository.save(article);

    }

    private Article getArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = getArticle(articleId);
        Blog blog = article.getBlog();
        updateNumberOfArticles(blog, -1);
        blogRepository.save(blog);
        articleRepository.deleteById(articleId);
    }
}
