package org.example.prj1.controller;

import jakarta.validation.Valid;

import org.example.prj1.dto.request.UserCreationRequest;
import org.example.prj1.dto.request.UserUpdateRequest;
import org.example.prj1.dto.response.TodoResponse;
import org.example.prj1.entity.Todo;
import org.example.prj1.entity.User;
import org.example.prj1.mapper.TodoMapper;
import org.example.prj1.repository.TodoRepository;
import org.example.prj1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TodoRepository todoRepository;
    @PostMapping
    public User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createRequest(request);
    }
    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy) {
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
         authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userService.getUser(page, size, sortBy);
    }
    @GetMapping("/{id}")

    public User getUser(@PathVariable int id) {

        return userService.getUser(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,@RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User Deleted";
    }
    @GetMapping("/{userId}/todos")
    public List<TodoResponse> getTodosByUser(@PathVariable int userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        List<TodoResponse> responses = new ArrayList<>();
        for (Todo todo : todos) {
            responses.add(TodoMapper.TodoResponse(todo));
        }
        return responses;
    }

}
