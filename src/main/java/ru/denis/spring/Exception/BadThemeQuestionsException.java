package ru.denis.spring.Exception;

public class BadThemeQuestionsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadThemeQuestionsException(final String message) {
        super(message);
    }
}
