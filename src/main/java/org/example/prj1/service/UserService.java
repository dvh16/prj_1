package org.example.prj1.service;


import org.example.prj1.dto.request.UserCreationRequest;
import org.example.prj1.dto.request.UserUpdateRequest;
import org.example.prj1.dto.response.UserResponse;
import org.example.prj1.entity.User;
import org.example.prj1.exception.AppException;
import org.example.prj1.exception.ErrorCode;
import org.example.prj1.mapper.UserMapper;
import org.example.prj1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private UserMapper userMapper;



public User createRequest(UserCreationRequest request)
{
    if(userRepository.existsByUsername(request.getUsername()))
        throw new AppException(ErrorCode.BAD_REQUEST);
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    user.setEmail(request.getEmail());
    return userRepository.save(user);
}

public User updateUser(int id, UserUpdateRequest request) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    return userRepository.save(user);

}
public Page<User> getUser(Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy) {
    int pageindex = page.orElse(0);
    int pagesize = size.orElse(5);
    return userRepository.findAll(
            PageRequest.of(
                    pageindex,
            pagesize,
            Sort.Direction.ASC, sortBy.orElse("id")
    ));
}
public User getUser(int id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

}
public void deleteUser(int id) {
    userRepository.deleteById(id);
}

}
