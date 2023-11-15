package fr.brenard.blogs.models.forms;

import lombok.Data;

@Data
public class ArticleForm {

    private String title;
    private String content;
    private boolean isVisible;

}
