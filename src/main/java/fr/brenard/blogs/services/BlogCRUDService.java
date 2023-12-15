package fr.brenard.blogs.services;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.forms.blogs.BlogCreationForm;
import fr.brenard.blogs.models.forms.blogs.BlogUpdateForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogCRUDService {

    List<BlogDTO> getAll();
    BlogDTO getBlogById(Long id);
    ResponseEntity<String> createAndSetupNewBlog(BlogCreationForm form);
    ResponseEntity<String> updateBlogTitle(BlogUpdateForm form);
    void deleteBlogByUserId(Long Userid);



}
