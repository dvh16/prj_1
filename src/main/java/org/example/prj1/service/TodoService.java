package org.example.prj1.service;

import jakarta.transaction.Transactional;
import org.example.prj1.enums.TodoStatus;
import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.dto.request.TodoUpdateRequest;
import org.example.prj1.dto.response.TodoResponse;
import org.example.prj1.entity.Todo;
import org.example.prj1.entity.User;
import org.example.prj1.exception.AppException;
import org.example.prj1.exception.ErrorCode;
import org.example.prj1.exception.ErrorTodo;
import org.example.prj1.mapper.TodoMapper;
import org.example.prj1.repository.TodoRepository;
import org.example.prj1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(TodoCreationRequest request) {
        if (todoRepository.existsByContent(request.getContent()))
            throw new AppException(ErrorTodo.BAD_REQUEST);
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Todo todo = TodoMapper.toEntity(request, user);
        return todoRepository.save(todo);
    }

    public TodoResponse updateTodo(int id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorTodo.NOT_FOUND));
        todo.setContent(request.getContent());
        todo.setStatus(request.getStatus());
        todoRepository.save(todo);
        return TodoMapper.TodoResponse(todo);
    }

    @Transactional
    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> responses = new ArrayList<>();
        for (Todo todo : todos) {
            responses.add(TodoMapper.TodoResponse(todo));
        }
        return responses;
    }

    @Transactional
    public TodoResponse getTodo(int id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return TodoMapper.TodoResponse(todo);
    }

    public void deleteTodo(int id) {
        if (!todoRepository.existsById(id)) {
            throw new AppException(ErrorCode.NOT_EXIST);
        }
        todoRepository.deleteById(id);
    }

    public List<TodoResponse> filterTodos(TodoStatus status, LocalDate date) {

        List<Todo>todos;
        if (status != null && date != null) {
            todos = todoRepository.findByStatusAndCreatedAt(status, date);
        } else if (status != null)
            todos = todoRepository.findByStatus(status);
        else if (date != null)
            todos =  todoRepository.findByCreatedAt(date);
        else todos = todoRepository.findAll();
        return todos.stream()
                .map(TodoMapper::TodoResponse)
                .collect(Collectors.toList());
    }

}
