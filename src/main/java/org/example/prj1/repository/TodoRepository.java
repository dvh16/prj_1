package org.example.prj1.repository;

import org.example.prj1.TodoStatus;
import org.example.prj1.entity.Todo;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
   List<Todo> findByStatus(TodoStatus status);
    List<Todo> findByCreatedAt(LocalDate createdAt);
    List<Todo> findByStatusAndCreatedAt(TodoStatus status, LocalDate createdAt);
    boolean existsByContent(String content);
    List<Todo> findByUserId(int userId);
}
