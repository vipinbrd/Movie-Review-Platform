package com.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviereview.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
