package com.moviereview.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.moviereview.dto.ReviewResponse;
import com.moviereview.entity.User;
import com.moviereview.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{movieId}")
    public ResponseEntity<ReviewResponse> submitReview(
            @PathVariable Long movieId,
            @RequestParam String content,
            @RequestParam int rating,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                reviewService.submitReview(movieId, content, rating, user.getEmail())
        );
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsForMovie(movieId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("deleted");
    }
}
