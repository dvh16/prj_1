package org.example.prj1.service;


import org.example.prj1.dto.request.UserCreationRequest;
import org.example.prj1.dto.request.UserUpdateRequest;
import org.example.prj1.dto.response.UserResponse;
import org.example.prj1.entity.User;
import org.example.prj1.mapper.UserMapper;
import org.example.prj1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private UserMapper userMapper;



public User createRequest(UserCreationRequest request)
{
    if(userRepository.existsByUsername(request.getUsername()))
        throw new ApplicationContextException("Username already exists");
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    user.setEmail(request.getEmail());
    return userRepository.save(user);
}

public UserResponse updateUser(int id, UserUpdateRequest request) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    UserResponse userResponse = new UserResponse();
    userResponse.setUsername(user.getUsername());
    userResponse.setEmail(user.getEmail());
    return userResponse;

}
public List<User> getUser() {
    return userRepository.findAll();
}
public UserResponse getUser(int id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    UserResponse userResponse = new UserResponse();
    userResponse.setUsername(user.getUsername());
    userResponse.setEmail(user.getEmail());
    return userResponse;


}
public void deleteUser(int id) {
    userRepository.deleteById(id);
}

}
