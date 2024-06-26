package ru.denis.spring.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.denis.spring.Exception.*;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class BadRequestControllerAdvice {

    private final MessageSource messageSource;
    protected static final Logger LOGGER = LoggerFactory.getLogger(BadRequestControllerAdvice.class);

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(HttpServletRequest request,
                                                             BindException exception,
                                                             Locale locale) {
        LOGGER.info("BindException occured: URL="+request.getRequestURL());

        String message = this.messageSource.getMessage("error.400.title", new Object[0],"error.400.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST,message);

        problemDetail.setProperty("errors",
                exception.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
    @ExceptionHandler(NoUserExistsException.class)
    public ResponseEntity<ProblemDetail>  handleNonExistingUserException(HttpServletRequest request,
                                                                         NoUserExistsException e,
                                                                         Locale locale) {
        LOGGER.info("NoUserExistsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handlerUserAlreadyExistsException(HttpServletRequest request,
                                                                           UserAlreadyExistsException e,
                                                                         Locale locale) {
        LOGGER.info("UserAlreadyExistsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
    @ExceptionHandler(NoQuestionExistsException.class)
    public ResponseEntity<ProblemDetail>  handleNoQuestionExistsException(HttpServletRequest request,
                                                                          NoQuestionExistsException e,
                                                                         Locale locale) {
        LOGGER.info("NoQuestionExistsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
    @ExceptionHandler(QuestionAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail>  handleQuestionAlreadyExistsException(HttpServletRequest request,
                                                                               QuestionAlreadyExistsException e,
                                                                         Locale locale) {
        LOGGER.info("QuestionAlreadyExistsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
    @ExceptionHandler(BadThemeQuestionsException.class)
    public ResponseEntity<ProblemDetail>  handleBadThemeQuestionsException(HttpServletRequest request,
                                                                           BadThemeQuestionsException e,
                                                                         Locale locale) {
        LOGGER.info("BadThemeQuestionsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
}
