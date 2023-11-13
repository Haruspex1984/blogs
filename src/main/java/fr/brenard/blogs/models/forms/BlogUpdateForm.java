package fr.brenard.blogs.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogUpdateForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;


}
