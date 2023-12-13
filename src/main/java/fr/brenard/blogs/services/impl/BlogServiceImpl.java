package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.ForbiddenWordsException;
import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.blogs.BlogCreationForm;
import fr.brenard.blogs.models.forms.blogs.BlogForm;
import fr.brenard.blogs.models.forms.blogs.BlogUpdateForm;
import fr.brenard.blogs.tools.ForbiddenWordsVerifier;
import fr.brenard.blogs.tools.mappers.BlogMapper;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.BlogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
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
            Blog newBlog = new Blog();
            setUpNewBlog(newBlog, form);
            return ResponseEntity.ok("Blog créé avec succès");
        } catch (ForbiddenWordsException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().body("Le titre du blog contient un mot non autorisé");
        }
    }

    private void setUpNewBlog(Blog newBlog, BlogCreationForm form) throws ForbiddenWordsException {

        verifyAndSetBlogTitle(newBlog, form);
        newBlog.setCreationDate(LocalDate.now());
        newBlog.setNumberOfArticles(0);
        updateUserWithNewBlog(form.getUserId(), newBlog);

    }

    private void verifyAndSetBlogTitle(Blog blog, BlogForm form) throws ForbiddenWordsException {

        String title = form.getTitle();

        if (ForbiddenWordsVerifier.containsForbiddenWords(title)) {
            throw new ForbiddenWordsException("Ce titre contient un mot non autorisé");
        }
        blog.setTitle(title);
    }

    private void updateUserWithNewBlog(Long userId, Blog newBlog) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBlog(newBlog);
        blogRepository.save(newBlog);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> updateBlogTitle(BlogUpdateForm form) {

        Blog blog = getBlogByUserId(form.getUserId());

        try {
            verifyAndSetBlogTitle(blog, form);
            blogRepository.save(blog);
            return ResponseEntity.ok("Succès");
        } catch (ForbiddenWordsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    private Blog getBlogByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getBlog();
    }

    @Override
    public void deleteBlogByUserId(Long userId) {
        blogRepository.deleteBlogByUserId(userId);
    }


}
