package ru.denis.spring.Controller.payload;

public record UpdateAnswerPayload(
      String answerHeader,
      String answerBody
) { }
