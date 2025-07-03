package com.moviereview.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MovieResponse {

    private Long id;

    private String title;

    private String description;

    private String genre;

    private LocalDate releaseDate;

    private String posterUrl;

    private Double averageRating;

    private LocalDateTime createdAt;
}
