package ru.stackquestions.spring.Controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserPayload(
    @NotNull(message = "error")
    @Size(min = 3, max = 50, message = "failed email")
    String email,
    @Size(min = 3, max = 30, message = "failed nameUser")
    String nameUser,
    @Size(min = 3, max = 30, message = "failed password")
    String password) {}
