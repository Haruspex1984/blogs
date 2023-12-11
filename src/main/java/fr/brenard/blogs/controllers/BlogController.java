package fr.brenard.blogs.controllers;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.forms.BlogForm;
import fr.brenard.blogs.models.forms.BlogUpdateForm;
import fr.brenard.blogs.services.BlogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/all")
    public List<BlogDTO> getAll(){
        return blogService.getAll();
    }

    @GetMapping("/{id}")
    public BlogDTO getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBlog(@RequestBody @Valid BlogForm form){
        blogService.createAndSetupNewBlog(form);
    }

    @PatchMapping("/{userId}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBlogTitle(@RequestBody @Valid BlogUpdateForm form){
        blogService.updateBlogTitle(form);

    }

    @Transactional
    @DeleteMapping("/{userId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable Long userId){
        blogService.deleteBlogByUserId(userId);
    }



}
