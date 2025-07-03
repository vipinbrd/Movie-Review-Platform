package com.moviereview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moviereview.dto.ReviewResponse;
import com.moviereview.entity.Movie;
import com.moviereview.entity.Review;
import com.moviereview.entity.User;
import com.moviereview.repository.MovieRepository;
import com.moviereview.repository.ReviewRepository;
import com.moviereview.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final MovieRepository movieRepo;
    private final UserRepository userRepo;

    @Override
    public ReviewResponse submitReview(Long movieId, String content, int rating, String userEmail) {
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = Review.builder()
                .content(content)
                .rating(rating)
                .movie(movie)
                .user(user)
                .build();

        review = reviewRepo.save(review);

        List<Review> allReviews = reviewRepo.findAllByMovieId(movieId);
        double avg = allReviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
        movie.setAverageRating(avg);
        movieRepo.save(movie);

        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt(),
                user.getName()
        );
    }

    @Override
    public List<ReviewResponse> getReviewsForMovie(Long movieId) {
        return reviewRepo.findAllByMovieId(movieId).stream()
                .map(r -> new ReviewResponse(
                        r.getId(),
                        
                       r.getContent(),
                        r.getRating(),
                        
                        r.getCreatedAt(),
                        r.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

       
     
        reviewRepo.delete(review);
    }
}
