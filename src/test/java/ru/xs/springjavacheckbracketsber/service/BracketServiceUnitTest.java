package ru.xs.springjavacheckbracketsber.service;

import org.junit.jupiter.api.Test;
import ru.xs.springjavacheckbracketsber.model.InputText;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketServiceUnitTest {

    private final BracketServiceImpl bracketServiceImpl = new BracketServiceImpl();

    @Test
    void checkBracket_False_OnlyOpeningBrackets() {
        final InputText inputText = new InputText("(");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_MismatchedBlankBrackets() {
        final InputText inputText = new InputText("()[]{}");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_BalancedBlankBrackets() {
        final InputText inputText = new InputText("([{}])");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_WithOtherCharacters() {
        final InputText inputText = new InputText("([{a}])");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_NestedMismatchedBrackets() {
        final InputText inputText = new InputText("()(])");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_UnbalancedBrackets() {
        final InputText inputText = new InputText("({[}]())");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_ValidInputText() {
        final InputText inputText = new InputText("abc(def)");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_UnclosedBracket() {
        final InputText inputText = new InputText("abc(def");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_OpeningBracketMissing() {
        final InputText inputText = new InputText("(abc(def)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_SimpleInputText() {
        final InputText inputText = new InputText("(text)");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_TextOnly() {
        final InputText inputText = new InputText("text");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_BlankInputText() {
        final InputText inputText = new InputText("()");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_WhitespaceInputText() {
        final InputText inputText = new InputText("(     )");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_WhitespaceAroundText() {
        final InputText inputText = new InputText("(  text  )");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_NestedBracketsWithSpaces() {
        final InputText inputText = new InputText("(text)(abc( )def)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_ComplexInputText() {
        final InputText inputText = new InputText("(text)(abc(  def  )ghi (jkl))");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_BlankBracketInComplexInputText() {
        final InputText inputText = new InputText("(text)(abc(  def  )ghi ( ))");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_UnclosedBracketAtEnd() {
        final InputText inputText = new InputText("(text)(");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_UnclosedBracketAtStart() {
        final InputText inputText = new InputText("text)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_False_BracketOutOfOrder() {
        final InputText inputText = new InputText(")text)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

    @Test
    void checkBracket_True_LongComplexInputText() {
        final InputText inputText = new InputText("Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. " +
                "Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). " +
                "В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. " +
                "Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, " +
                "(как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими, " +
                "особенно когда мы достигли высшей точки и увидели прекрасный вид на долину " +
                "(я почувствовал, что все усилия стоили того).");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(inputText));
    }

}
