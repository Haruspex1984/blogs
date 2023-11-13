package fr.brenard.blogs.models.forms;

import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;

@Data
public class BlogForm {

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9,'-.?!\\s]*$")
    private String title;
    private LocalDate creationDate;
    private Long userId;

}
