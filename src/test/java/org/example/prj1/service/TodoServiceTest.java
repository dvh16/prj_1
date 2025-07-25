package org.example.prj1.service;

import org.assertj.core.api.Assertions;
import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.entity.Todo;
import org.example.prj1.entity.User;
import org.example.prj1.enums.TodoStatus;
import org.example.prj1.exception.AppException;
import org.example.prj1.repository.TodoRepository;
import org.example.prj1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest

public class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @MockBean
    private UserRepository userRepository;
    private TodoCreationRequest request;
    private Todo todo;
    private User user;


    @BeforeEach
    void initData()
    {
        request = TodoCreationRequest.builder()
                .content("test")
                .status(TodoStatus.PENDING)
                .createdAt(LocalDate.parse("2025-07-16"))
                .userId(1)
                .build();


        this.todo = new Todo();
        todo.setContent("test");
        todo.setCreatedAt(LocalDate.parse("2025-07-16"));
        todo.setStatus(TodoStatus.PENDING);

        user = User.builder()
                .id(1)
                .email("huy")
                .username("test")
                .build();
    }

    @Test
    void createTodo_validRequest_success()
    {
        //GIVEN
        Mockito.when(userRepository.existsById(1)).thenReturn(true);
        Mockito.when(todoRepository.
                existsByUser_IdAndContent(request.getUserId(), request.getContent()))
                .thenReturn(false);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(todoRepository.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));

        //WHEN

        Todo result = todoService.createTodo(request);

        //THEN

        Assertions.assertThat(result.getUser().getId()).isEqualTo(1);
        Assertions.assertThat(result.getContent()).isEqualTo("test");

    }

    @Test
    void createTodo_todoExist_fail()
    {
        //GIVEN
        Mockito.when(userRepository.existsById(1)).thenReturn(true);
        Mockito.when(todoRepository.existsByUser_IdAndContent(1, "test")).thenReturn(true);

        //WHEN
        Assertions.assertThatThrownBy(() -> todoService.createTodo(request))
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Content already exists");

    }

    @Test
    void createTodo_userNotExist_fail()
    {
        //GIVEN
        Mockito.when(userRepository.existsById(1)).thenReturn(false);

        Assertions.assertThatThrownBy(() -> todoService.createTodo(request))
                .isInstanceOf(AppException.class)
                .hasMessageContaining("User not found");
    }

}
