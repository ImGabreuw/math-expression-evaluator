public class Evaluator {

    private final Stack<Double> variables = new StaticStack<>();

    public double evaluate(String postfixExpression, double[] values) {
        for (char token : postfixExpression.toCharArray()) {
            if (Character.isLetter(token)) {
                int variableIndex = token - 'A';
                double variableValue = values[variableIndex];
                variables.push(variableValue);
                continue;
            }

            if (Utils.isOperator(token)) {
                double operand2 = variables.pop();
                double operand1 = variables.pop();
                double result = apply(token, operand1, operand2);
                variables.push(result);
            }
        }

        return variables.pop();
    }

    private double apply(char operator, double operand1, double operand2) {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Operação inválida. Divisão por 0.");
                }

                yield operand1 / operand2;
            }
            case '^' -> Math.pow(operand1, operand2);
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }

}
