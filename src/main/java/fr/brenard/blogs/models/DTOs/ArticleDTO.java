package fr.brenard.blogs.models.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private boolean isVisible;
    private Long blogId;
    private Long UserId;

}
