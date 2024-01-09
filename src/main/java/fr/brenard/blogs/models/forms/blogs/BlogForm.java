package fr.brenard.blogs.models.forms.blogs;

import lombok.Data;

@Data
public abstract class BlogForm {

    private Long userId;
    private String title;
    private String description;

}
