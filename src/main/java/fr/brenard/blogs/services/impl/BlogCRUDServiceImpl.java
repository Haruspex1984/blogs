package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.ForbiddenWordsException;
import fr.brenard.blogs.exceptions.UserNotFoundException;
import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.blogs.BlogForm;
import fr.brenard.blogs.services.SharedService;
import fr.brenard.blogs.tools.ForbiddenWordsVerifier;
import fr.brenard.blogs.tools.mappers.BlogMapper;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.BlogCRUDService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@Service
public class BlogCRUDServiceImpl implements BlogCRUDService {

    private final BlogRepository blogRepository;
    private final SharedService sharedService;


    public BlogCRUDServiceImpl(BlogRepository blogRepository, UserRepository userRepository, SharedService sharedService) {
        this.blogRepository = blogRepository;
        this.sharedService = sharedService;
    }


    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.findAll().stream().map(BlogMapper::fromEntity).toList();
    }


    @Override
    public BlogDTO getBlogById(Long id) {
        return blogRepository.findById(id).map(BlogMapper::fromEntity).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ResponseEntity<String> createNewBlogFromForm(BlogForm form) {

        try {
            User blogOwner = sharedService.findUserById(form.getUserId());
            Blog newBlog = new Blog();
            blogOwner.setBlog(newBlog);
            ForbiddenWordsVerifier.verifyTitle(form.getTitle());
            setUpNewBlogFromForm(newBlog, form);
            sharedService.saveBlog(newBlog);
            sharedService.saveUser(blogOwner);

            return ResponseEntity.created(getBlogURI(newBlog)).body("Blog created");
        } catch (ForbiddenWordsException e) {
            return ResponseEntity.badRequest().body("Title contains forbidden words");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    private void setUpNewBlogFromForm(Blog blog, BlogForm form) {
        blog.setTitle(form.getTitle());
        blog.setDescription(form.getDescription());
        blog.setCreationDate(LocalDate.now());
        blog.setNumberOfArticles(0);
    }

    private URI getBlogURI(Blog blog) {
        return URI.create("/blogs/" + blog.getId());
    }

    @Override
    public ResponseEntity<String> updateBlogInfo(BlogForm form) {

        try {
            Blog blogToUpdate = sharedService.findBlogById(form.getUserId());
            ForbiddenWordsVerifier.verifyTitle(form.getTitle());
            blogToUpdate.setTitle(form.getTitle());
            blogToUpdate.setDescription(form.getDescription());
            sharedService.saveBlog(blogToUpdate);
            return ResponseEntity.ok("Blog updated!");
        } catch (ForbiddenWordsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No blog with id " + form.getUserId());
        }
    }


    @Override
    public void deleteBlogByUserId(Long userId) {
        blogRepository.deleteBlogByUserId(userId);
    }


}
