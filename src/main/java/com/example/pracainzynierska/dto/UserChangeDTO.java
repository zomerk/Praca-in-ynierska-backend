package com.example.pracainzynierska.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserChangeDTO {
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Fitness level cannot be empty")
    private String fitnessLevel; // Use String if you want to transfer the Enum as a String

    private String goal;
}

