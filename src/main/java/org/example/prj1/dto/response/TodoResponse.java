package org.example.prj1.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private String content;
    private Boolean completed;
    private LocalDateTime createdAt;
    private String username;
    private int id;
}
