package org.example.prj1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoUpdateRequest {
    private String content;
    private boolean completed;
    private int id;
    private LocalDateTime createdAt;

}
