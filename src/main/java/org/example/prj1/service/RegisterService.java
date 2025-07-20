package org.example.prj1.service;

import org.example.prj1.dto.request.RegisterRequest;
import org.example.prj1.entity.User;
import org.example.prj1.enums.Roles;
import org.example.prj1.exception.AppException;
import org.example.prj1.exception.ErrorCode;
import org.example.prj1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        HashSet<String> roles = new HashSet<>();
        roles.add(Roles.USER.name());
        user.setRoles(roles);
        userRepository.save(user);
    }
}
