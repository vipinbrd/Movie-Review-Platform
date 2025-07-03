package com.moviereview.service;

import org.springframework.stereotype.Service;

import com.moviereview.dto.AuthRequest;
import com.moviereview.dto.AuthResponse;
import com.moviereview.dto.RegisterRequest;
@Service
public interface AuthService {
	public AuthResponse register(RegisterRequest req);
	public AuthResponse login(AuthRequest req);

}
