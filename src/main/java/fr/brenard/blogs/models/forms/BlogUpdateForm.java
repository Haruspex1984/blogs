package fr.brenard.blogs.models.forms;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlogUpdateForm {

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9,'-.?!\\s]*$")
    private String title;


}
