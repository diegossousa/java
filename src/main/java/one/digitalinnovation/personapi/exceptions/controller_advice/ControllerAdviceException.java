package one.digitalinnovation.personapi.exceptions.controller_advice;

import one.digitalinnovation.personapi.exceptions.message.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;

@ControllerAdvice
public class ControllerAdviceException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> authenticationException(BadCredentialsException exception) {
        return new ResponseEntity<>(
                ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .error(exception.getCause().getMessage())
                        .status(String.valueOf(HttpStatus.FORBIDDEN.value()))
                        .timestamp(Calendar.getInstance().getTime().toString())
                        .build().toString(),
                HttpStatus.FORBIDDEN
        );
    }
}
