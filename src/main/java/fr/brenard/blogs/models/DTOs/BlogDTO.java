package fr.brenard.blogs.models.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BlogDTO {

    private Long id;
    private String title;
    private LocalDate creationDate;
    private Long userId;

}
