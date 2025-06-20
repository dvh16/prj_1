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
public class TodoCreationRequest {
    private int userId;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;



}
