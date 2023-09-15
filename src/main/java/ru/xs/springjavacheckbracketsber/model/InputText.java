package ru.xs.springjavacheckbracketsber.model;

import jakarta.validation.constraints.NotBlank;

public record InputText(@NotBlank(message = "Не должно быть пустым и содержать только пробелы, не должно быть null")
                        String inputText) {
}
