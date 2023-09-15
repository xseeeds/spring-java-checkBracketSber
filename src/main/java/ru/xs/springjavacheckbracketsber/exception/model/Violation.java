package ru.xs.springjavacheckbracketsber.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static ru.xs.springjavacheckbracketsber.exception.model.ApiError.PATTERN_DATE_TIME;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Violation {

    @JsonFormat(pattern = PATTERN_DATE_TIME)
    LocalDateTime timestamp;

    HttpStatus status;

    String reason;

    String fieldName;

    String message;

}