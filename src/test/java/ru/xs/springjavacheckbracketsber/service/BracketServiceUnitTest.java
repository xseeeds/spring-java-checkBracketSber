package ru.xs.springjavacheckbracketsber.service;

import org.junit.jupiter.api.Test;
import ru.xs.springjavacheckbracketsber.model.InputText;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketServiceUnitTest {

    private final BracketServiceImpl bracketServiceImpl = new BracketServiceImpl();

    @Test
    void checkBracket_False_OnlyOpeningBrackets() {
        final InputText text = new InputText("(");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_MismatchedBlankBrackets() {
        final InputText text = new InputText("()[]{}");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_BalancedBlankBrackets() {
        final InputText text = new InputText("([{}])");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_WithOtherCharacters() {
        final InputText text = new InputText("([{a}])");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_NestedMismatchedBrackets() {
        final InputText text = new InputText("()(])");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_UnbalancedBrackets() {
        final InputText text = new InputText("({[}]())");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_ValidInputText() {
        final InputText text = new InputText("abc(def)");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_UnclosedBracket() {
        final InputText text = new InputText("abc(def");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_OpeningBracketMissing() {
        final InputText text = new InputText("(abc(def)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_SimpleInputText() {
        final InputText text = new InputText("(text)");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_TextOnly() {
        final InputText text = new InputText("text");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_BlankInputText() {
        final InputText text = new InputText("()");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_WhitespaceInputText() {
        final InputText text = new InputText("(     )");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_WhitespaceAroundText() {
        final InputText text = new InputText("(  text  )");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_NestedBracketsWithSpaces() {
        final InputText text = new InputText("(text)(abc( )def)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_ComplexInputText() {
        final InputText text = new InputText("(text)(abc(  def  )ghi (jkl))");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_BlankBracketInComplexInputText() {
        final InputText text = new InputText("(text)(abc(  def  )ghi ( ))");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_UnclosedBracketAtEnd() {
        final InputText text = new InputText("(text)(");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_UnclosedBracketAtStart() {
        final InputText text = new InputText("text)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_False_BracketOutOfOrder() {
        final InputText text = new InputText(")text)");
        assertFalse(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

    @Test
    void checkBracket_True_LongComplexInputText() {
        final InputText text = new InputText("Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. " +
                "Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). " +
                "В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. " +
                "Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, " +
                "(как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими, " +
                "особенно когда мы достигли высшей точки и увидели прекрасный вид на долину " +
                "(я почувствовал, что все усилия стоили того).");
        assertTrue(bracketServiceImpl.checkStackBracketMapCycle(text));
    }

}
