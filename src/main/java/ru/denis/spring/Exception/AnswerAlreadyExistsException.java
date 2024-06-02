package ru.denis.spring.Exception;

public class AnswerAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AnswerAlreadyExistsException(final String message) {
        super(message);
    }
}