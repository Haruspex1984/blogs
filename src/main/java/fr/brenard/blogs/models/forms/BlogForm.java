package fr.brenard.blogs.models.forms;

import fr.brenard.blogs.tools.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;

@Data
public class BlogForm {



    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    private LocalDate creationDate;
    private Long userId;

}
