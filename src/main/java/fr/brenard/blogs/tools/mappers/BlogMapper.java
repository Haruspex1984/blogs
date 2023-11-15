package fr.brenard.blogs.tools.mappers;

import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;

public class BlogMapper {
    public static BlogDTO fromEntity(Blog entity){
        return BlogDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .creationDate(entity.getCreationDate())
                .userId(entity.getUser().getId())
                .build();
    }
}
