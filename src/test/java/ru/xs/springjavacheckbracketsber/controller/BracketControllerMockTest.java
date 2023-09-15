package ru.xs.springjavacheckbracketsber.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.xs.springjavacheckbracketsber.conroller.BracketControllerImpl;
import ru.xs.springjavacheckbracketsber.model.InputText;
import ru.xs.springjavacheckbracketsber.service.BracketService;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(bracketService.checkStackBracketMapCycle(any(InputText.class)))
                .thenReturn(true);

        assertThat(bracketControllerImpl.checkBracket(new InputText("(*)∫(*)")))
                .isTrue();

        verify(bracketService, atMostOnce())
                .checkStackBracketMapCycle(any(InputText.class));
    }

    @Test
    public void testCheckBracketWhenReturnFalse() {
        when(bracketService.checkStackBracketMapCycle(any(InputText.class)))
                .thenReturn(false);

        assertThat(bracketControllerImpl.checkBracket(new InputText("(*)∫(*)")))
                .isFalse();

        verify(bracketService, atMostOnce())
                .checkStackBracketMapCycle(any(InputText.class));
    }

}
