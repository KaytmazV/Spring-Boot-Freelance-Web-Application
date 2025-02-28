package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogPostDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private String author;
    private LocalDateTime createdDate;
    private List<String> tags;
}

