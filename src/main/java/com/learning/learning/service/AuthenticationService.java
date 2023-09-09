package com.learning.learning.service;

import com.learning.learning.dao.JwtAuthenticationResponse;
import com.learning.learning.dao.SigningRequest;
import com.learning.learning.dao.SignupRequest;
import com.learning.learning.entity.Role;
import com.learning.learning.entity.User;
import com.learning.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationResponse signup(SignupRequest request) {
    var user = User
        .builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    userRepo.save(user);

    var jwt = jwtService.generateToken(user);

    return JwtAuthenticationResponse.builder().token(jwt).build();
  }

  public JwtAuthenticationResponse signin(SigningRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = userRepo
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid user name or password!"));
    var jwt = jwtService.generateToken(user);

    return JwtAuthenticationResponse.builder().token(jwt).build();
  }
}
