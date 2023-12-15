package fr.brenard.blogs.controllers;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.forms.blogs.BlogCreationForm;
import fr.brenard.blogs.models.forms.blogs.BlogUpdateForm;
import fr.brenard.blogs.services.BlogCRUDService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogCRUDService blogCRUDService;

    public BlogController(BlogCRUDService blogCRUDService) {
        this.blogCRUDService = blogCRUDService;
    }

    @GetMapping("/all")
    public List<BlogDTO> getAll() {
        return blogCRUDService.getAll();
    }

    @GetMapping("/{id}")
    public BlogDTO getBlogById(@PathVariable Long id) {
        return blogCRUDService.getBlogById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestBody @Valid BlogCreationForm form) {
        return blogCRUDService.createAndSetupNewBlog(form);
    }

    @PatchMapping("/{userId}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> updateBlogTitle(@RequestBody @Valid BlogUpdateForm form) {
       return blogCRUDService.updateBlogTitle(form);
    }

    @Transactional
    @DeleteMapping("/{userId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable Long userId) {
        blogCRUDService.deleteBlogByUserId(userId);
    }


}
