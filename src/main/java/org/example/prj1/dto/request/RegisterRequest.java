package org.example.prj1.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.prj1.enums.Roles;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    public String username;

    @Size(min = 8, message = "password must be at least 8 characters")
    public String password;

    public String email;
    Roles role;
}
