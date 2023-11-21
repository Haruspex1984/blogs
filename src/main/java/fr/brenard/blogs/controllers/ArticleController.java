package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.DTOs.ArticleDTO;
import fr.brenard.blogs.models.forms.ArticleForm;
import fr.brenard.blogs.services.ArticleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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


    @PostMapping("/{userId}/newArticle")
    public void createAndSetUpNewArticle(@RequestBody @Valid ArticleForm form, @PathVariable Long userId) {
        System.out.println(form);
        articleService.createAndSetUpNewArticle(form, userId);
    }

    @PatchMapping("/{articleId}/update")
    public void updateArticle(@RequestBody @Valid ArticleForm form, @PathVariable Long articleId) {
        articleService.updateArticle(form, articleId);
    }

    @Transactional
    @DeleteMapping("/{articleId}/delete")
    public void deleteArticle(@PathVariable Long articleId){
        articleService.deleteArticle(articleId);
    }
}
