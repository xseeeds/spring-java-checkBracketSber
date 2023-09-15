package ru.xs.springjavacheckbracketsber.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.xs.springjavacheckbracketsber.model.InputText;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BracketIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void checkBracket_ValidInputEmpty_ReturnsBadRequest() {
        final InputText inputText = new InputText("");

        mockMvc.perform(get("/checkBracket")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputText)))
                .andExpectAll(status()
                                .isBadRequest(),
                        jsonPath("$..fieldName").value("inputText"),
                        jsonPath("$..message")
                                .value("Не должно быть пустым и содержать только пробелы, не должно быть null"));

    }

    @Test
    @SneakyThrows
    public void checkBracket_ValidInputBlank_ReturnsBadRequest() {
        final InputText inputText = new InputText(" ");

        mockMvc.perform(get("/checkBracket")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputText)))
                .andExpectAll(status()
                                .isBadRequest(),
                        jsonPath("$..fieldName").value("inputText"),
                        jsonPath("$..message")
                                .value("Не должно быть пустым и содержать только пробелы, не должно быть null"));

    }

    @Test
    @SneakyThrows
    public void checkBracket_ValidInputNull_ReturnsBadRequest() {
        final InputText inputText = new InputText(null);

        mockMvc.perform(get("/checkBracket")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputText)))
                .andExpectAll(status()
                                .isBadRequest(),
                        jsonPath("$.[*]").isArray(),
                        jsonPath("$.violations.[*]", hasSize(1)),
                        jsonPath("$.violations.[0].fieldName", equalTo("inputText")),
                        jsonPath("$.violations.[0].message",
                                equalTo("Не должно быть пустым и содержать только пробелы, не должно быть null")));
    }

    @Test
    @SneakyThrows
    public void checkBracket_ValidInput_ReturnsTrue() {
        final InputText inputText = new InputText("Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями." +
                " Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия)." +
                " В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели." +
                " Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками," +
                " (как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими," +
                " особенно когда мы достигли высшей точки и увидели прекрасный вид на долину" +
                " (я почувствовал, что все усилия стоили того).");

        mockMvc.perform(get("/checkBracket")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputText)))
                .andExpectAll(status()
                                .isOk(),
                        content().string(String.valueOf(true)));
    }

    @Test
    @SneakyThrows
    public void checkBracket_ValidInput_ReturnsFalse() {
        final InputText inputText = new InputText("Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями." +
                " Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия)." +
                " В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели." +
                " Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками," +
                " (как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими," +
                " особенно когда мы достигли высшей точки и увидели прекрасный вид на долину" +
                " (я почувствовал, что все усилия стоили того).)");

        mockMvc.perform(get("/checkBracket")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputText)))
                .andExpectAll(status()
                                .isOk(),
                        content().string(String.valueOf(false)));
    }

}

