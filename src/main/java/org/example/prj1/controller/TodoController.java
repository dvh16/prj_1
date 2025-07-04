package org.example.prj1.controller;



import org.example.prj1.enums.TodoStatus;
import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.dto.request.TodoUpdateRequest;
import org.example.prj1.dto.response.TodoResponse;
import org.example.prj1.entity.Todo;
import org.example.prj1.repository.TodoRepository;
import org.example.prj1.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;
    @PostMapping
    public Todo createTodo(@RequestBody TodoCreationRequest request) {
        return todoService.createTodo(request);
    }
    @GetMapping
    public List<TodoResponse> getAllTodos() {

        return todoService.getAllTodos();
    }
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable int id) {
        return todoService.getTodo(id);
    }
    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable int id, @RequestBody TodoUpdateRequest request) {
        return todoService.updateTodo(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
        return "User Deleted";
    }
    @GetMapping("/filter")
    public List<TodoResponse> getFilteredTodos(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            )
    {
        return todoService.filterTodos(status, date);
    }
}
