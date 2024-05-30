package ru.stackquestions.spring.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stackquestions.spring.Exception.NoUserExistsException;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class BadRequestControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
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
        System.out.println("NoUserExistsException occured: URL="+request.getRequestURL());
        String message = this.messageSource.getMessage("error.404.title", new Object[0],"error.404.title",locale);

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,message);

        problemDetail.setProperty("errors", e.getLocalizedMessage());

        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

}
