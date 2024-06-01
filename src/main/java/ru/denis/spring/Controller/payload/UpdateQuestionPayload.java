package ru.denis.spring.Controller.payload;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateQuestionPayload (
        @Nullable
        String questionHeader,
        @Nullable
        String questionBody,
        @Nullable
        List<String> theme
) { }
