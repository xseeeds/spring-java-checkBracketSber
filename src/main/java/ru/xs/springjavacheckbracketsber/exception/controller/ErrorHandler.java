package ru.xs.springjavacheckbracketsber.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.xs.springjavacheckbracketsber.exception.model.ApiError;
import ru.xs.springjavacheckbracketsber.exception.model.ValidationApiError;
import ru.xs.springjavacheckbracketsber.exception.model.Violation;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice("ru.xs.springjavacheckbracketsber")
public class ErrorHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationApiError errorMethodArgumentNotValidException(
            final MethodArgumentNotValidException e
    ) {
        log.warn("ErrorHandler => " + e.getMessage(), e);
        final List<Violation> errorRequestBody = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Violation
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .status(BAD_REQUEST)
                        .reason("ErrorHandler => errorMethodArgumentNotValidException")
                        .fieldName(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(toList());
        return ValidationApiError
                .builder()
                .violations(errorRequestBody)
                .build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiError errorInternalServerErrorException(final Throwable e) {
        log.warn("ErrorHandler => " + e.getMessage(), e);
        return ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(INTERNAL_SERVER_ERROR)
                .reason("ErrorHandler => errorInternalServerErrorException")
                .message("Произошла непредвиденная ошибка")
                .build();
    }

}