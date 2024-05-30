package ru.stackquestions.spring.Controller.payload;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

public record UpdateUserPayload(
    @Nullable
    @Size(min = 3, max = 50, message = "failed email")
    String email,
    @Nullable
    @Size(min = 3, max = 30, message = "failed nameUser")
    String nameUser,
    @Nullable
    @Size(min = 3, max = 30, message = "failed password")
    String password) {}
