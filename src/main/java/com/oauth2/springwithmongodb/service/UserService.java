package com.oauth2.springwithmongodb.service;

import com.oauth2.springwithmongodb.dtos.*;
import com.oauth2.springwithmongodb.entity.User;
import com.oauth2.springwithmongodb.repository.UserRepository;
import com.oauth2.springwithmongodb.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Register
    public User register(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email Already Exists");
        }

        User newUser = User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword())) // hash password
                .build();

        return userRepository.save(newUser);
    }

    // ✅ Login
    public JwtResponse login(LoginDTO loginDTO) {
        String email = loginDTO.email().trim().toLowerCase();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new JwtResponse(accessToken, refreshToken, user);
    }





    // ✅ Get all users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build())
                .toList();
    }

    // ✅ Get user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // ✅ Delete user
    public String deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            return "User not found!";
        }
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }

    // ✅ Update user
    public String update(UpdateDTO updateDTO) {
        User existingUser = userRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        existingUser.setName(updateDTO.getName());
        existingUser.setEmail(updateDTO.getEmail());

        // Update password only if present
        if (updateDTO.getPassword() != null && !updateDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        }

        userRepository.save(existingUser);

        return "User Updated Successfully";
    }

    public String refreshAccessToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        String username = jwtUtil.extractUsername(refreshToken);
        return jwtUtil.generateToken(username);
    }

}
