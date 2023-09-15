package ru.xs.springjavacheckbracketsber.conroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.xs.springjavacheckbracketsber.model.InputText;
import ru.xs.springjavacheckbracketsber.service.BracketService;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Validated
@RestController
@RequestMapping("/checkBracket")
@RequiredArgsConstructor
public class BracketControllerImpl implements BracketController {

    private final BracketService bracketsService;

    @Override
    @GetMapping
    @ResponseStatus(OK)
    public boolean checkBracket(@RequestBody @Valid InputText input) {
        log.info("Controller => Received request to /checkBracket");
        final boolean result = bracketsService.checkStackBracketMapCycle(input);
        log.info(result ? "Controller => Bracket are balanced" :
                "Controller => Bracket are not balanced or no bracket pairs found");
        return result;
    }

}
