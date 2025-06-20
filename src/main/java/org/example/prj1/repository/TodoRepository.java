package org.example.prj1.repository;

import org.example.prj1.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    boolean existsByContent(String content);
    List<Todo> findByUserId(int userId);

}
