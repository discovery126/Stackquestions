package ru.denis.spring.Exception;

public class NoUserExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoUserExistsException(final String message) {
        super(message);
    }
}