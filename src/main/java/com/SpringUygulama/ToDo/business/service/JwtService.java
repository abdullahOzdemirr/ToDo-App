package com.SpringUygulama.ToDo.business.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    public String generateMailToken(String email);
    public String extractEmail(String token);
}
