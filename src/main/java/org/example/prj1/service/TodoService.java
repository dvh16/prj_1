package org.example.prj1.service;

import jakarta.transaction.Transactional;
import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.dto.request.TodoUpdateRequest;
import org.example.prj1.dto.response.TodoResponse;
import org.example.prj1.entity.Todo;
import org.example.prj1.entity.User;
import org.example.prj1.mapper.TodoMapper;
import org.example.prj1.repository.TodoRepository;
import org.example.prj1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(TodoCreationRequest request) {
        if (todoRepository.existsByContent(request.getContent()))
            throw new ApplicationContextException("Content already exists");
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = TodoMapper.toEntity(request, user);
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }

    public TodoResponse updateTodo(int id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content with id " + id + " not found"));
        todo.setContent(request.getContent());
        todo.setCompleted(request.isCompleted());
    todoRepository.save(todo);
        return TodoMapper.TodoResponse(todo);
    }

    @Transactional
    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> responses = new ArrayList<>();
        for(Todo todo : todos) {
            responses.add(TodoMapper.TodoResponse(todo));
        }
        return responses;
    }
@Transactional
    public TodoResponse getTodo(int id)
    {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return TodoMapper.TodoResponse(todo);
    }

    public void deleteTodo(int id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo with id " + id + " does not exist");
        }
        todoRepository.deleteById(id);
    }
}
