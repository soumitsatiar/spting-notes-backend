package org.example.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "title is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "only alphabets allowed")
    private String username;

    @NotBlank(message = "description is required")
    private String password;

}
