package dev.anuradha.omsuserservice.service;

import dev.anuradha.omsuserservice.dto.RegisterUserRequest;
import dev.anuradha.omsuserservice.entity.User;
import dev.anuradha.omsuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User register(RegisterUserRequest registerUserRequest) {

        User user = User.builder()
                .name(registerUserRequest.getName())
                .email(registerUserRequest.getEmail())
                .password(registerUserRequest.getPassword())
                .phone(registerUserRequest.getPhone())
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
