package com.volkankaytmaz.backendproject2.service;

import com.volkankaytmaz.backendproject2.dto.BlogPostDTO;
import com.volkankaytmaz.backendproject2.entity.BlogPost;
import com.volkankaytmaz.backendproject2.mapper.BlogPostMapper;
import com.volkankaytmaz.backendproject2.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    public List<BlogPostDTO> getAllBlogPosts() {
        return blogPostRepository.findAll().stream()
                .map(blogPostMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BlogPostDTO getBlogPostById(Long id) {
        return blogPostRepository.findById(id)
                .map(blogPostMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Blog post not found with id: " + id));
    }

    public BlogPostDTO createBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostMapper.toEntity(blogPostDTO);
        BlogPost savedBlogPost = blogPostRepository.save(blogPost);
        return blogPostMapper.toDTO(savedBlogPost);
    }

    public BlogPostDTO updateBlogPost(Long id, BlogPostDTO blogPostDTO) {
        if (!blogPostRepository.existsById(id)) {
            throw new RuntimeException("Blog post not found with id: " + id);
        }
        BlogPost blogPost = blogPostMapper.toEntity(blogPostDTO);
        blogPost.setId(id);
        BlogPost updatedBlogPost = blogPostRepository.save(blogPost);
        return blogPostMapper.toDTO(updatedBlogPost);
    }

    public void deleteBlogPost(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new RuntimeException("Blog post not found with id: " + id);
        }
        blogPostRepository.deleteById(id);
    }
}

