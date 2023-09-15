package ru.xs.springjavacheckbracketsber.conroller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.xs.springjavacheckbracketsber.model.InputText;
import ru.xs.springjavacheckbracketsber.model.Response;

import static org.springframework.http.HttpStatus.OK;

public interface BracketController {

    @GetMapping
    @ResponseStatus(OK)
    Response checkBracket(@RequestBody @Valid InputText text);

}
