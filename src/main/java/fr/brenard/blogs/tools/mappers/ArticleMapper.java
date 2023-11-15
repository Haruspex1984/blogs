package fr.brenard.blogs.tools.mappers;

import fr.brenard.blogs.models.DTOs.ArticleDTO;
import fr.brenard.blogs.models.entities.Article;

public class ArticleMapper {

    public static ArticleDTO fromEntity(Article entity){
        return ArticleDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .isVisible(entity.isVisible())
                .UserId(entity.getUser().getId())
                .blogId(entity.getBlog().getId())
                .build();
    }

}
