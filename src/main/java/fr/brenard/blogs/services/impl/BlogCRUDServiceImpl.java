package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.ForbiddenWordsException;
import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.blogs.BlogCreationForm;
import fr.brenard.blogs.models.forms.blogs.BlogUpdateForm;
import fr.brenard.blogs.services.BlogValidationService;
import fr.brenard.blogs.tools.mappers.BlogMapper;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.BlogCRUDService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogCRUDServiceImpl implements BlogCRUDService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final BlogValidationService validationService;

    public BlogCRUDServiceImpl(BlogRepository blogRepository, UserRepository userRepository, BlogValidationService validationService) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.validationService = validationService;
    }


    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.findAll().stream().map(BlogMapper::fromEntity).toList();
    }


    @Override
    public BlogDTO getBlogById(Long id) {
        try {
            return blogRepository.findById(id).map(BlogMapper::fromEntity).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No blog with id " + id);
        }
    }

    @Override
    public ResponseEntity<String> createAndSetupNewBlog(BlogCreationForm form) {
        try {
            Blog blog = new Blog();
            User user = findUserById(form.getUserId());

            user.setBlog(blog);

            validationService.verifyTitle(form.getTitle());

            setUpNewBlogFromForm(blog,form);

            blogRepository.save(blog);
            userRepository.save(user);

            return ResponseEntity.ok("Blog has been created");
        } catch (ForbiddenWordsException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().body("Forbidden word in blog title");
        }catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    private void setUpNewBlogFromForm(Blog blog,BlogCreationForm form){
        blog.setTitle(form.getTitle());
        blog.setDescription(form.getDescription());
        blog.setCreationDate(LocalDate.now());
        blog.setNumberOfArticles(0);
    }


    @Override
    public ResponseEntity<String> updateBlogInfo(BlogUpdateForm form) {
        Blog blog = getBlogByUserId(form.getUserId());
        try {
            validationService.verifyTitle(form.getTitle());
            blog.setTitle(form.getTitle());
            blog.setDescription(form.getDescription());
            blogRepository.save(blog);
            return ResponseEntity.ok("Blog updated!");
        } catch (ForbiddenWordsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    private Blog getBlogByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return user.getBlog();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteBlogByUserId(Long userId) {
        blogRepository.deleteBlogByUserId(userId);
    }


}
