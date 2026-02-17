package dev.anuradha.omsuserservice.service;

import dev.anuradha.omsuserservice.config.JwtUtil;
import dev.anuradha.omsuserservice.dto.LoginRequest;
import dev.anuradha.omsuserservice.dto.LoginResponse;
import dev.anuradha.omsuserservice.dto.RegisterUserRequest;
import dev.anuradha.omsuserservice.entity.User;
import dev.anuradha.omsuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User register(RegisterUserRequest registerUserRequest) {

        log.info("Registering user with email: {}", registerUserRequest.getEmail());
        userRepository.findByEmail(registerUserRequest.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        User user = User.builder()
                .name(registerUserRequest.getName())
                .email(registerUserRequest.getEmail())
                .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .phone(registerUserRequest.getPhone())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword()) ){
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }


}
