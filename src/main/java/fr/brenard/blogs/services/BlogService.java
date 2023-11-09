package fr.brenard.blogs.services;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.forms.BlogForm;

import java.util.List;

public interface BlogService {

    List<BlogDTO> getAll();
    BlogDTO getBlogById(Long id);
    void createBlog(BlogForm form);

}
