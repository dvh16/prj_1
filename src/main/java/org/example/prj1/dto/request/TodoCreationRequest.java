package org.example.prj1.dto.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.prj1.enums.TodoStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoCreationRequest {
    private int userId;
    private String content;
    private TodoStatus status;
    private LocalDate createdAt;


}
