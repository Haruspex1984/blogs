package fr.brenard.blogs.models.forms;

import fr.brenard.blogs.tools.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlogUpdateForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    private Long userId;


}
