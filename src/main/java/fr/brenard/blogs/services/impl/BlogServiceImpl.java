package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.DTOs.BlogDTO;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.entities.User;
import fr.brenard.blogs.models.forms.BlogForm;
import fr.brenard.blogs.models.forms.BlogUpdateForm;
import fr.brenard.blogs.tools.mappers.BlogMapper;
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
        try{
            return blogRepository.findById(id).map(BlogMapper::fromEntity).orElseThrow(EntityNotFoundException::new);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("No blog with id "+id);
        }

    }


    @Override
    public void createAndSetupNewBlog(BlogForm form) {
        Blog newBlog = new Blog();
        setUpNewBlog(newBlog,form);
    }

    private void setUpNewBlog(Blog newBlog, BlogForm form){
        newBlog.setCreationDate(LocalDate.now());
        newBlog.setTitle(form.getTitle());
        newBlog.setNumberOfArticles(0);
        updateUserWithNewBlog(form.getUserId(), newBlog);
    }

    private void updateUserWithNewBlog(Long userId, Blog newBlog) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBlog(newBlog);

        blogRepository.save(newBlog);
        userRepository.save(user);
    }


    @Override
    public void updateBlogTitle(BlogUpdateForm form) {
        Blog blog = getBlogByUserId(form.getUserId());
        blog.setTitle(form.getTitle());
        System.out.println(blog);
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
