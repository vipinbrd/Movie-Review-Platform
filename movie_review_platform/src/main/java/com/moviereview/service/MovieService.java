package com.moviereview.service;

import com.moviereview.dto.MovieRequest;
import com.moviereview.dto.MovieResponse;
import org.springframework.data.domain.Page;

public interface MovieService {
    Page<MovieResponse> getAllMovies(int page, int size, String sortBy);
    MovieResponse getMovieById(Long id);
    MovieResponse addMovie(MovieRequest movieRequest);
    void deleteMovie(Long id);

}
