package fr.brenard.blogs.models.forms;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class BlogForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    private LocalDate creationDate;
    private Long userId;

}
