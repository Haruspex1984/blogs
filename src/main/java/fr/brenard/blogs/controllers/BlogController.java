package fr.brenard.blogs.controllers;


import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.forms.BlogForm;
import fr.brenard.blogs.services.BlogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/all")
    public List<BlogDTO> getAll(){
        return blogService.getAll();
    }

    @RequestMapping("/{id}")
    public BlogDTO getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @PostMapping("/create")
    public void createBlog(@RequestBody @Valid BlogForm form){
        blogService.createBlog(form);
    }

    @Transactional
    @DeleteMapping("/{userId}/delete")
    public void deleteBlog(@PathVariable Long userId){
        blogService.deleteBlogByUserId(userId);
    }



}
