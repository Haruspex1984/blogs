package fr.brenard.blogs.models.forms;

import fr.brenard.blogs.tools.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlogUpdateForm {

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = Constants.BLOG_TITLE_REGEX)
    private String title;


}
