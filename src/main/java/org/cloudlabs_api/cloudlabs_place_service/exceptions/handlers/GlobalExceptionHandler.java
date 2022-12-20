package org.cloudlabs_api.cloudlabs_place_service.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.cloudlabs_api.cloudlabs_place_service.commons.Converter;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.*;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.ApiExceptionResponse;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.ApiExceptionResponseArguments;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problem;
import org.cloudlabs_api.cloudlabs_place_service.exceptions.models.Problems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleConflict(Exception exception, HttpServletRequest request) {
        Problem problem = new Problem("Error genérico", exception.getMessage(), request.getRequestURI());
        return this.registerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, problem, exception.getClass().toString());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> notFoundExceptionHandler(NotFoundException exception) {
        return this.registerException(exception, HttpStatus.NOT_FOUND, exception.getProblem(), exception.getClass().toString());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiExceptionResponse> badRequestExceptionHandler(BadRequestException exception) {
        return this.registerException(exception, HttpStatus.BAD_REQUEST, exception.getProblem(), exception.getClass().toString());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiExceptionResponse> conflictExceptionHandler(ConflictException exception) {
        return this.registerException(exception, HttpStatus.CONFLICT, exception.getProblem(), exception.getClass().toString());
    }

    @ExceptionHandler(CommunicationException.class)
    public ResponseEntity<ApiExceptionResponse> communicationExceptionHandler(CommunicationException exception) {
        return this.registerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getProblem(), exception.getClass().toString());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiExceptionResponse> jsonProcessingExceptionHandler(CommunicationException exception) {
        return this.registerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getProblem(), exception.getClass().toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponseArguments> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        Map<String, String> detail = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            detail.put(fieldName, errorMessage);

        });

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        ApiExceptionResponseArguments error = new ApiExceptionResponseArguments(HttpStatus.BAD_REQUEST,
                Problems.METHOD_ARGUMENT_NOT_VALID,
                exception.getClass().toString(),
                sw.toString().replaceAll("\\r\\n\\t|\\r\\n|\\n\\t", " "),
                detail);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiExceptionResponse> registerException(Exception exception, HttpStatus httpStatus, Problem problem, String type) {
        long ownId = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        hashMap.put("OwnID", ownId);
        hashMap.put("Exception", problem.getTitle());
        hashMap.put("StackTrace", sw.toString());

        log.error(Converter.convertValue(hashMap));

        ApiExceptionResponse error = new ApiExceptionResponse(httpStatus, problem, type, sw.toString().replaceAll("\\r\\n\\t|\\r\\n|\\n\\t", " "));

        return new ResponseEntity<>(error, httpStatus);
    }
}
