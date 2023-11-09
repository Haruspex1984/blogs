package fr.brenard.blogs.models.DTOs;

import fr.brenard.blogs.models.entities.Blog;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String emailAddress;
    private LocalDate registerDate;
    private BlogDTO blogDTO;

}
