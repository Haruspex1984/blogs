package fr.brenard.blogs.tools.mappers;

import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;

import java.util.stream.Collectors;

public class BlogMapper {
    public static BlogDTO fromEntity(Blog entity){
        return BlogDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .userId(entity.getUser().getId())
                .numberOfArticles(entity.getNumberOfArticles())
                .articles(entity.getArticles().stream().map(ArticleMapper::fromEntity).collect(Collectors.toSet()))
                .build();
    }
}
