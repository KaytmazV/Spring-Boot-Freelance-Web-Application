package com.volkankaytmaz.backendproject2.repository;

import com.volkankaytmaz.backendproject2.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}

