import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {

    private Evaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new Evaluator();
    }

    @Test
    void shouldEvaluateSimpleExpression() {
        String postfixExpression = "AB+";
        double[] values = {3, 5}; // A = 3, B = 5

        double result = evaluator.evaluate(postfixExpression, values);

        assertEquals(8, result);
    }

    @Test
    void shouldEvaluateComplexExpression() {
        String postfixExpression = "AB+CD-/E*";
        double[] values = {7, 3, 6, 4, 9};

        double result = evaluator.evaluate(postfixExpression, values);

        assertEquals(45, result);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenIsInvalidNumberOfOperands() {
        String postfixExpression = "AB+C-*";
        double[] values = {3, 5, 2}; // A = 3, B = 5, C = 2

        assertThrows(IllegalStateException.class, () -> evaluator.evaluate(postfixExpression, values));
    }

    @Test
    void shouldEvaluateExpressionWithDivideByZero() {
        String postfixExpression = "AB/C/";
        double[] values = {3, 0, 2}; // A = 3, B = 0, C = 2

        assertThrows(ArithmeticException.class, () -> evaluator.evaluate(postfixExpression, values));
    }

    @Test
    void shouldEvaluateExpressionWithPowerOperator() {
        String postfixExpression = "AB^";
        double[] values = {2, 3}; // A = 2, B = 3

        double result = evaluator.evaluate(postfixExpression, values);

        assertEquals(8, result);
    }

}