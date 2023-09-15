package ru.xs.springjavacheckbracketsber.model;

import lombok.Builder;

@Builder
public record Response(boolean isCorrect) {
}
