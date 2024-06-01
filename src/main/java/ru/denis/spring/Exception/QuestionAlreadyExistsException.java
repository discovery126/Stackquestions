package ru.denis.spring.Exception;

public class QuestionAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QuestionAlreadyExistsException(final String message) {
        super(message);
    }
}