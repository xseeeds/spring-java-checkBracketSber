package ru.xs.springjavacheckbracketsber.conroller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.xs.springjavacheckbracketsber.model.InputText;

import static org.springframework.http.HttpStatus.OK;

public interface BracketController {

    @GetMapping
    @ResponseStatus(OK)
    boolean checkBracket(@RequestBody @Valid InputText input);

}
