package org.example.prj1.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.prj1.TodoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private String content;
    private TodoStatus status;
    private LocalDate createdAt;
    private String username;
    private int id;
}
