package com.reelnest.service;

import com.reelnest.dto.RegisterRequest;
import com.reelnest.dto.UserDto;
import com.reelnest.model.User;
import com.reelnest.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService{
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto register(RegisterRequest req) {
        String email = req.getEmail().toLowerCase().trim();
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        User u = new User();
        u.setEmail(email);
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        u.setRole("USER");
        repo.save(u);

        return new UserDto(u.getId(), u.getEmail(), u.getRole(), u.getCreatedAt());
    }
}