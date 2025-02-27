package com.volkankaytmaz.backendproject2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;
    private String category;
    private String author;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ElementCollection
    @CollectionTable(name = "blog_post_tags",
            joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private List<String> tags;
}