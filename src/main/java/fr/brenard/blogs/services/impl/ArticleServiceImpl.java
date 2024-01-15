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
import fr.brenard.blogs.services.SharedService;
import fr.brenard.blogs.tools.mappers.ArticleMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final SharedService sharedService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, BlogRepository blogRepository, SharedService sharedService) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.sharedService = sharedService;
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
    public void createNewArticle(ArticleForm form) {

        Article article = new Article();
        User user = sharedService.getUserById(form.getUserId());
        Blog blog = user.getBlog();

        article.setBlog(blog);

        setUpNewArticle(article, form);

        updateNumberOfArticles(blog, +1);

        sharedService.saveArticle(article);
        sharedService.saveBlog(blog);
    }

    private void setUpNewArticle(Article article, ArticleForm form) {
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setVisible(form.isVisible());
        article.setCreationDate(LocalDateTime.now());
        article.setUser(sharedService.getUserById(form.getUserId()));
    }


    private void updateNumberOfArticles(Blog blog, int amount) {
        blog.setNumberOfArticles(blog.getNumberOfArticles() + amount);
    }


    @Override
    public void updateArticle(ArticleForm form, Long articleId) {

        Article article = sharedService.getArticleById(articleId);

        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setVisible(form.isVisible());
        article.setLastUpdateDate(LocalDateTime.now());

        sharedService.saveArticle(article);

    }


    @Override
    public void deleteArticle(Long articleId) {
        Article article = sharedService.getArticleById(articleId);
        Blog blog = article.getBlog();
        updateNumberOfArticles(blog, -1);
        articleRepository.deleteById(articleId);
        sharedService.saveBlog(blog);
    }
}
