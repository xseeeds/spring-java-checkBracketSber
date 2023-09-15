package ru.xs.springjavacheckbracketsber.exception.model;

import lombok.Builder;

import java.util.List;

@Builder
public record ValidationApiError(List<Violation> violations) {
}