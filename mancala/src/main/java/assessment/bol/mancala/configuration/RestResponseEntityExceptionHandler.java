package assessment.bol.mancala.configuration;

import assessment.bol.mancala.dto.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IncorrectArgumentException.class, IncorrectArgumentException.class})
    protected ResponseEntity<Object> handleConflict(
            IncorrectArgumentException ex, WebRequest request) {
//        String bodyOfResponse = "I have conflicting feelings about it";
        return handleExceptionInternal(ex, ex.getBody(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {BadRequestException.class, BadRequestException.class})
    protected ResponseEntity<Object> badRequest(
            BadRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getBody(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {InternalServerErrorException.class, InternalServerErrorException.class})
    protected ResponseEntity<Object> internalServerError(
            InternalServerErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getBody(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = {IllegalMoveException.class, IllegalMoveException.class})
    protected ResponseEntity<Object> illegalMove(
            IllegalMoveException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getBody(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value
            = {ResourceNotFoundException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> illegalMove(
            ResourceNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getBody(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}