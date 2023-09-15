package ru.xs.springjavacheckbracketsber.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError {

    @JsonFormat(pattern = PATTERN_DATE_TIME)
    LocalDateTime timestamp;

    HttpStatus status;

    String reason;

    String message;

    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
}
