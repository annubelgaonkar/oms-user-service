package dev.anuradha.omsuserservice.controller;

import dev.anuradha.omsuserservice.dto.ApiResponse;
import dev.anuradha.omsuserservice.dto.LoginRequest;
import dev.anuradha.omsuserservice.dto.LoginResponse;
import dev.anuradha.omsuserservice.dto.RegisterUserRequest;
import dev.anuradha.omsuserservice.entity.User;
import dev.anuradha.omsuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(
            @RequestBody RegisterUserRequest registerUserRequest){

        User user = userService.register(registerUserRequest);

        return ResponseEntity.ok(
                ApiResponse.<User>builder()
                        .success(true)
                        .data(user)
                        .message("User registered successfully")
                        .build()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable UUID id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(
          ApiResponse.<LoginResponse>builder()
                  .success(true)
                  .data(response)
                  .message("Login successful")
                  .build()
        );
    }
}
