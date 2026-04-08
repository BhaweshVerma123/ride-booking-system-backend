package com.example.project.uber.uberApp.services;

import com.example.project.uber.uberApp.entities.User;
import com.example.project.uber.uberApp.exception.ResourceNotFoundException;
import com.example.project.uber.uberApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository; // ✅ MUST be final

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ✅ fetch user from DB
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}