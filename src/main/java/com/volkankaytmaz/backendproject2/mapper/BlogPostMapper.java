package com.volkankaytmaz.backendproject2.mapper;

import com.volkankaytmaz.backendproject2.dto.BlogPostDTO;
import com.volkankaytmaz.backendproject2.entity.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    BlogPostMapper INSTANCE = Mappers.getMapper(BlogPostMapper.class);

    BlogPostDTO toDTO(BlogPost blogPost);
    BlogPost toEntity(BlogPostDTO blogPostDTO);
}

