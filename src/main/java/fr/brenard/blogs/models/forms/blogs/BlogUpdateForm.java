package fr.brenard.blogs.models.forms.blogs;

import fr.brenard.blogs.tools.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogUpdateForm extends BlogForm{

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    private Long userId;


}
