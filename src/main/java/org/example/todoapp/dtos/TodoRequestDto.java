package org.example.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoRequestDto {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

}
