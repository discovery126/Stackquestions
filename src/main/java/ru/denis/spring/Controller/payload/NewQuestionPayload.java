package ru.denis.spring.Controller.payload;

import java.util.List;

public record NewQuestionPayload (
      String questionHeader,
      String questionBody,
      List<String> theme
) { }
