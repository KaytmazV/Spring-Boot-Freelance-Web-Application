package com.volkankaytmaz.backendproject2.controller;

import com.volkankaytmaz.backendproject2.dto.BlogPostDTO;
import com.volkankaytmaz.backendproject2.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blogpost")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public ResponseEntity<List<BlogPostDTO>> getAllBlogPosts() {
        List<BlogPostDTO> blogPosts = blogPostService.getAllBlogPosts();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getBlogPostById(@PathVariable Long id) {
        BlogPostDTO blogPost = blogPostService.getBlogPostById(id);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogPostDTO> createBlogPost(@RequestBody BlogPostDTO blogPostDTO) {
        BlogPostDTO createdBlogPost = blogPostService.createBlogPost(blogPostDTO);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> updateBlogPost(@PathVariable Long id, @RequestBody BlogPostDTO blogPostDTO) {
        BlogPostDTO updatedBlogPost = blogPostService.updateBlogPost(id, blogPostDTO);
        return new ResponseEntity<>(updatedBlogPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

