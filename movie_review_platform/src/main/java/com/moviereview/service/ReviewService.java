package com.moviereview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviereview.dto.ReviewResponse;
@Service
public interface ReviewService {
	
	public ReviewResponse submitReview(Long movieId, String content, int rating, String userEmail);
	 public List<ReviewResponse> getReviewsForMovie(Long movieId);
	 public void deleteReview(Long id);
}
