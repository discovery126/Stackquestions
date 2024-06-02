package ru.denis.spring.Controller.payload;

import java.util.List;

public record NewAnswerPayload(
      String answerHeader,
      String answerBody
) { }
