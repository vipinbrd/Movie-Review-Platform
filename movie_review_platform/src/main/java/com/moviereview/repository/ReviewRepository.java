package com.moviereview.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviereview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findAllByMovieId(Long movieId);


}
