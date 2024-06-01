package ru.denis.spring.Controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserPayload(
    @NotNull(message = "error")
    @Size(min = 3, max = 50, message = "email length must be more 3 and less 50")
    String email,
    @Size(min = 3, max = 30, message = "nameUser length must be more 3 and less 30")
    String nameUser,
    @Size(min = 3, max = 30, message = "password length must be more 3 and less 30")
    String password) {}
