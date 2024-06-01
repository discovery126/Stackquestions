package ru.denis.spring.Controller.payload;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

public record UpdateUserPayload(
    @Nullable
    @Size(min = 3, max = 50, message = "email length must be more 3 and less 50")
    String email,
    @Nullable
    @Size(min = 3, max = 30, message = "nameUser length must be more 3 and less 30")
    String nameUser,
    @Nullable
    @Size(min = 3, max = 30, message = "password length must be more 3 and less 30")
    String password) {}
