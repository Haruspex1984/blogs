package fr.brenard.blogs.models.DTOs;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class BlogDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Long userId;
    private int numberOfArticles;
    private Set<ArticleDTO> articles;


}
