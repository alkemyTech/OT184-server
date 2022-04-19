package com.alkemy.ong.web.exceptions;

import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> HandleParamNotFound(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("Resource Not Found")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), errorDTO.getStatus(), request);
    }

    @Data
    @AllArgsConstructor
    public static class ApiErrorDTO {

        private HttpStatus status;
        private String message;
        private List<String> errors;
    }
}
