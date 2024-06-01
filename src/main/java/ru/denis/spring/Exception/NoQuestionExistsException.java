package ru.denis.spring.Exception;

public class NoQuestionExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoQuestionExistsException(final String message) {
        super(message);
    }
}