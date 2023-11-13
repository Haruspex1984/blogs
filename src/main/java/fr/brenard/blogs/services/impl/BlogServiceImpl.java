package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.BlogForm;
import fr.brenard.blogs.models.forms.BlogUpdateForm;
import fr.brenard.blogs.models.mappers.BlogMapper;
import fr.brenard.blogs.repositories.BlogRepository;
import fr.brenard.blogs.repositories.UserRepository;
import fr.brenard.blogs.services.BlogService;
import jakarta.persistence.EntityNotFoundException;
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
        return blogRepository.findById(id).map(BlogMapper::fromEntity).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public void createBlog(BlogForm form) {
        Blog newBlog = createNewBlog(form);
        updateUserWithBlog(form.getUserId(), newBlog);
    }
    private Blog createNewBlog(BlogForm form) {
        Blog newBlog = new Blog();
        newBlog.setCreationDate(LocalDate.now());
        newBlog.setTitle(form.getTitle());
        return blogRepository.save(newBlog);
    }
    private void updateUserWithBlog(Long userId, Blog newBlog) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBlog(newBlog);
        userRepository.save(user);
    }


    @Override
    public void updateBlog(Long userId,BlogUpdateForm form) {
        Blog blog = getBlogByUserId(userId);
        blog.setTitle(form.getTitle());
        blogRepository.save(blog);
    }
    private Blog getBlogByUserId(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return user.getBlog();
    }


    @Override
    public void deleteBlogByUserId(Long userId) {
        blogRepository.deleteBlogByUserId(userId);
    }

}
