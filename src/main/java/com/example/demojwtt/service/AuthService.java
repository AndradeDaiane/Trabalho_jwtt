package com.example.demojwtt.service;

import com.example.demojwtt.model.User;
import com.example.demojwtt.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Autentica o usuário e gera um token JWT se as credenciais forem válidas.
     * @param username Nome de usuário
     * @param rawPassword Senha em texto plano
     * @return Token JWT
     * @throws BadCredentialsException Se usuário não existir ou senha estiver incorreta
     */
    public String authenticateUserAndGenerateToken(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("❌ Usuário não encontrado."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("❌ Senha incorreta.");
        }

        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}

