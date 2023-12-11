package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.DTOs.ArticleDTO;
import fr.brenard.blogs.models.entities.Article;
import fr.brenard.blogs.models.forms.ArticleForm;
import fr.brenard.blogs.services.ArticleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/all")
    public List<ArticleDTO> getAllArticles(){
        return articleService.getAll();
    }

    @GetMapping("/{articleId}")
    public ArticleDTO getArticleById(@PathVariable Long articleId){
        return articleService.getArticleById(articleId);
    }


    @PostMapping("/{userId}/newArticle")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAndSetUpNewArticle(@RequestBody @Valid ArticleForm form, @PathVariable Long userId) {
        articleService.createAndSetUpNewArticle(form, userId);
    }

    @PatchMapping("/{articleId}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateArticle(@RequestBody @Valid ArticleForm form, @PathVariable Long articleId) {
        articleService.updateArticle(form, articleId);
    }

    @Transactional
    @DeleteMapping("/{articleId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long articleId){
        articleService.deleteArticle(articleId);
    }
}
