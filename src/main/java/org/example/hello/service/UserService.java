package org.example.hello.service;

import org.example.hello.DTO_storage.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service

public class UserService {
    private final Map<Integer, UserDTO> store = new HashMap<>();
    private int idCounter;
    public UserDTO createUser(UserDTO user) {
        user.setId(idCounter++);
        System.out.println("Creating user with ID: " + user.getId());
        store.put(user.getId(),user);
        return user;
    }
    public UserDTO getUser(int id) {
        return store.get(id);
    }
    public UserDTO updateUser(int id, UserDTO user) {
        store.replace(id, user);
        return user;
    }
    public void deleteUser(int id) {
        store.remove(id);
    }
}
