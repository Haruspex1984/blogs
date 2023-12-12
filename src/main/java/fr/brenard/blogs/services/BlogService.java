package fr.brenard.blogs.services;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.forms.BlogForm;
import fr.brenard.blogs.models.forms.BlogUpdateForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {

    List<BlogDTO> getAll();
    BlogDTO getBlogById(Long id);
    ResponseEntity<String> createAndSetupNewBlog(BlogForm form);
    void updateBlogTitle(BlogUpdateForm form);
    void deleteBlogByUserId(Long Userid);



}
