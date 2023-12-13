package fr.brenard.blogs.models.forms.blogs;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogCreationForm extends BlogForm {



    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    private LocalDate creationDate;
    private Long userId;

}
