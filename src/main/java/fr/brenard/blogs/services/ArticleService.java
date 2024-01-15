package fr.brenard.blogs.services;

import fr.brenard.blogs.models.DTOs.ArticleDTO;
import fr.brenard.blogs.models.forms.ArticleForm;

import java.util.List;

public interface ArticleService {

 List<ArticleDTO> getAll();
 ArticleDTO getArticleById(Long id);
 void createNewArticle(ArticleForm form);
 void updateArticle(ArticleForm form, Long articleId);
 void deleteArticle(Long articleId);



}
