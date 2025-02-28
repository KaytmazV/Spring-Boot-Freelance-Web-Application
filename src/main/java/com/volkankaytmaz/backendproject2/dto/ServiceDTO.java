package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String category;
}

