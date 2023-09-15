package ru.xs.springjavacheckbracketsber.conroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.xs.springjavacheckbracketsber.model.InputText;
import ru.xs.springjavacheckbracketsber.model.Response;
import ru.xs.springjavacheckbracketsber.service.BracketService;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/checkBrackets")
@RequiredArgsConstructor
public class BracketControllerImpl implements BracketController {

    private final BracketService bracketsService;

    @Override
    @PostMapping
    @ResponseStatus(OK)
    public Response checkBracket(@RequestBody @Valid InputText text) {
        log.info("Controller => Received request to /api/checkBrackets");
        final boolean result = bracketsService.checkStackBracketMapCycle(text);
        log.info(result ? "Controller => Bracket are balanced" :
                "Controller => Bracket are not balanced or no bracket pairs found");
        return Response.builder().isCorrect(result).build();
    }

}
