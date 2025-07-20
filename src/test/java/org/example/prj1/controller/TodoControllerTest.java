package org.example.prj1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.prj1.Prj1Application;
import org.example.prj1.dto.request.TodoCreationRequest;
import org.example.prj1.entity.Todo;
import org.example.prj1.enums.TodoStatus;
import org.example.prj1.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private TodoCreationRequest request;
    private Todo todo;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initData()
    {
        request = TodoCreationRequest.builder()
                .content("test")
                .status(TodoStatus.PENDING)
                .createdAt(LocalDate.parse("2025-07-16"))
                .build();


        todo.setContent("test");
        todo.setCreatedAt(LocalDate.parse("2025-07-16"));
        todo.setStatus(TodoStatus.PENDING);
    }
    @Test
    void createTodo() throws Exception {
         //GIVEN
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        Mockito.when(todoService.createTodo(ArgumentMatchers.any())).
                thenReturn(todo);

                 //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/todos")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk()
                );

         //THEN
    }
}
