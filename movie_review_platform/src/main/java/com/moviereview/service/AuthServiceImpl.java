package com.moviereview.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviereview.dto.AuthRequest;
import com.moviereview.dto.AuthResponse;
import com.moviereview.dto.RegisterRequest;
import com.moviereview.entity.Role;
import com.moviereview.entity.User;
import com.moviereview.repository.UserRepository;
import com.moviereview.security.JwtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public AuthResponse register(RegisterRequest req) {
		boolean userExist = userRepo.findByEmail(req.getEmail()).isPresent();
		if (userExist)
			throw new RuntimeException("User Already registered");

		User user = User.builder().name(req.getName()).email(req.getEmail())
				.password(passwordEncoder.encode(req.getPassword())).role(Role.USER).build();
		userRepo.save(user);
		String token = jwtService.generateToken(user);
		return new AuthResponse(token);
	}

	@Override
	public AuthResponse login(AuthRequest req) {
		User user = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email"));

		if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
			throw new RuntimeException("Invalid password");

		String token = jwtService.generateToken(user);
		return new AuthResponse(token);
	}

}
