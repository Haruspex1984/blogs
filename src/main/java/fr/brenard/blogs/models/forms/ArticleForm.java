package fr.brenard.blogs.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleForm {

    @NotBlank
    @Size(min=3,max=100,message = "Le titre doit contenir entre 3 et 100 caractères.")
    private String title;
    @NotBlank
    private String content;
    private boolean visible;

}
