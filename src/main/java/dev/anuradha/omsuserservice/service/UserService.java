package dev.anuradha.omsuserservice.service;


import dev.anuradha.omsuserservice.dto.LoginRequest;
import dev.anuradha.omsuserservice.dto.LoginResponse;
import dev.anuradha.omsuserservice.dto.RegisterUserRequest;
import dev.anuradha.omsuserservice.entity.User;

import java.util.UUID;

public interface UserService {

    User register(RegisterUserRequest registerUserRequest);
    User getUser(UUID id);
    LoginResponse login(LoginRequest request);
}
