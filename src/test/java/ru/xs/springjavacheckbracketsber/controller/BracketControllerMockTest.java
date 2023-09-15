package ru.xs.springjavacheckbracketsber.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.xs.springjavacheckbracketsber.conroller.BracketControllerImpl;
import ru.xs.springjavacheckbracketsber.model.InputText;
import ru.xs.springjavacheckbracketsber.model.Response;
import ru.xs.springjavacheckbracketsber.service.BracketService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class BracketControllerMockTest {
    @Mock
    private BracketService bracketService;
    @InjectMocks
    private BracketControllerImpl bracketControllerImpl;

    @Test
    public void testCheckBracketWhenReturnTrue() {
        final Response responseTrue = Response.builder().isCorrect(true).build();

        when(bracketService.checkStackBracketMapCycle(any(InputText.class)))
                .thenReturn(responseTrue.isCorrect());

        assertThat(bracketControllerImpl.checkBracket(new InputText("(*)∫(*)")),
                equalTo(responseTrue));

        verify(bracketService, atMostOnce())
                .checkStackBracketMapCycle(any(InputText.class));
    }

    @Test
    public void testCheckBracketWhenReturnFalse() {
        final Response responseFalse = Response.builder().isCorrect(true).build();

        when(bracketService.checkStackBracketMapCycle(any(InputText.class)))
                .thenReturn(responseFalse.isCorrect());

        assertThat(bracketControllerImpl.checkBracket(new InputText("(*)∫(*)")),
                equalTo(responseFalse));

        verify(bracketService, atMostOnce())
                .checkStackBracketMapCycle(any(InputText.class));
    }

}
