package com.oauth2.springwithmongodb.controller;

import com.oauth2.springwithmongodb.dtos.*;
import com.oauth2.springwithmongodb.entity.User;
import com.oauth2.springwithmongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🟢 Register
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO requestDTO) {
        User user = userService.register(requestDTO);

        UserResponseDTO response = UserResponseDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt()).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){


       try {
          JwtResponse response = userService.login(loginDTO);

            return ResponseEntity.ok(response);
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }


    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> ser = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.FOUND).body(ser);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updates(@RequestBody UpdateDTO updateDTO, @PathVariable String id){
        String result = userService.update(updateDTO);

       return ResponseEntity.ok(result);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String newAccessToken = userService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }


}
