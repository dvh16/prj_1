package org.example.prj1.mapper;

import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.dto.response.TodoResponse;
import org.example.prj1.entity.Todo;
import org.example.prj1.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoMapper {
    public static Todo toEntity(TodoCreationRequest request, User user) {
        Todo todo = new Todo();
        todo.setContent(request.getContent());
        todo.setStatus(request.getStatus());
        todo.setCreatedAt(request.getCreatedAt());
        todo.setUser(user);
        return todo;
    }

        public static TodoResponse TodoResponse(Todo todo)
        {
            TodoResponse response = new TodoResponse();
            response.setId(todo.getId());
            response.setStatus(todo.getStatus());
            response.setContent(todo.getContent());
            response.setCreatedAt(todo.getCreatedAt());
            response.setUsername(todo.getUser().getUsername());
            return response;
        }
}
