package fr.brenard.blogs.controllers;

import fr.brenard.blogs.models.forms.ArticleForm;
import fr.brenard.blogs.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/{userId}/newArticle")
    public void createAndSetUpNewArticle(@RequestBody @Valid ArticleForm form, @PathVariable Long userId){
        articleService.createAndSetUpNewArticle(form, userId);
    }
}
