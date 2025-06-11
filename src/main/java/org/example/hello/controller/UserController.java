package org.example.hello.controller;


import lombok.RequiredArgsConstructor;
import org.example.hello.DTO_storage.UserDTO;
import org.springframework.web.bind.annotation.*;
import org.example.hello.service.UserService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;



    @PostMapping

    public UserDTO creatUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUsers(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}