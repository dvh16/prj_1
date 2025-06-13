package org.example.prj1.controller;

import jakarta.validation.Valid;

import org.example.prj1.dto.request.UserCreationRequest;
import org.example.prj1.dto.request.UserUpdateRequest;
import org.example.prj1.dto.response.UserResponse;
import org.example.prj1.entity.User;
import org.example.prj1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createRequest(request);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUser();
    }
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable int id,@RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User Deleted";
    }
}
