package com.moviereview.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    private String posterUrl;
}
