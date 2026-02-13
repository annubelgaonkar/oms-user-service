package dev.anuradha.omsuserservice.controller;

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
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest registerUserRequest){
        return ResponseEntity.ok(userService.register(registerUserRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUser(id));
    }

}
