package ru.denis.spring.Exception;

public class NoAnswerExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoAnswerExistsException(final String message) {
        super(message);
    }
}