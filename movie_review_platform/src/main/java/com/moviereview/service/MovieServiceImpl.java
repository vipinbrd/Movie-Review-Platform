package com.moviereview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviereview.dto.MovieRequest;
import com.moviereview.dto.MovieResponse;
import com.moviereview.entity.Movie;
import com.moviereview.exception.MovieNotFoundException;
import com.moviereview.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    @Override
    public Page<MovieResponse> getAllMovies(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Movie> moviePage = movieRepo.findAll(pageable);

        return moviePage.map(this::toResponse);
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        return toResponse(movie);
    }

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = toEntity(movieRequest);
        movie.setAverageRating(0.0);
        Movie saved = movieRepo.save(movie);
        return toResponse(saved);
    }

   
    private MovieResponse toResponse(Movie movie) {
        MovieResponse dto = new MovieResponse();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setAverageRating(movie.getAverageRating());
        dto.setCreatedAt(movie.getCreatedAt());
        return dto;
    }

    private Movie toEntity(MovieRequest req) {
        return Movie.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .genre(req.getGenre())
                .releaseDate(req.getReleaseDate())
                .posterUrl(req.getPosterUrl())
                .build();
    }

	@Override
	@Transactional
	public void deleteMovie(Long id) {
	    Movie movie = movieRepo.findById(id)
	            .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
	    movieRepo.delete(movie);
	}
}
