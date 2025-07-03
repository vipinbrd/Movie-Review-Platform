package com.moviereview.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data

public class ReviewResponse {
	   private Long id;
	    private String content;
	    private int rating;
	    private LocalDateTime createdAt;
	    private String userName;
	    
	    public ReviewResponse(Long id, String content, int rating, LocalDateTime createdAt, String userName) {
	        this.id = id;
	        this.content = content;
	        this.rating = rating;
	        this.createdAt = createdAt;
	        this.userName = userName;
	    }
}