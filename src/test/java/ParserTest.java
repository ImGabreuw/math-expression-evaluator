import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void shouldConvertInfixToPostfixNotion() {
        var expression = "A*(B+C)/D";

        assertEquals("ABC+*D/", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithOnlyLetters() {
        String expression = "ABC";
        assertEquals(expression, parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithParentheses() {
        String expression = "(A+B)*C";
        assertEquals("AB+C*", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithPrecedence() {
        String expression = "A+B*C";
        assertEquals("ABC*+", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithMultipleOperators() {
        String expression = "A+B*C/D-E";
        assertEquals("ABC*D/+E-", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithMultipleOperatorsAndParentheses() {
        String expression = "(A+B)*(C-D)/E";
        assertEquals("AB+CD-*E/", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithEmptyExpression() {
        String expression = "";
        assertEquals("", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithSingleOperator() {
        String expression = "+";
        assertThrows(IllegalArgumentException.class, () -> parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldConvertToPostfixNotationWithSingleLetter() {
        String expression = "A";
        assertEquals("A", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldReportInvalidOperator() {
        String expression = "A*(B%+C)/D";
        assertThrows(IllegalArgumentException.class, () -> parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldReportInvalidParentheses() {
        String expression = "((A*(B-C))/D";
        assertThrows(IllegalArgumentException.class, () -> parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldReportUnmatchedClosingParenthesis() {
        String expression = "(A*(B-C))/D)";
        assertThrows(IllegalArgumentException.class, () -> parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldHandlePowerOperator() {
        String expression = "A^B^C";
        assertEquals("AB^C^", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldHandlePowerOperatorWithOtherOperators() {
        String expression = "A+B^C-D";
        assertEquals("ABC^+D-", parser.toPostfixNotion(expression));
    }

    @Test
    public void shouldHandlePowerOperatorWithParentheses() {
        String expression = "(A+B)^C";
        assertEquals("AB+C^", parser.toPostfixNotion(expression));
    }

}