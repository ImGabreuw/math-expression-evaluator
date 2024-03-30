public class Parser {

    private final Stack<Character> operators = new StaticStack<>();
    private final StringBuilder postfix = new StringBuilder();


    public String toPostfixNotion(String expression) {
        int openParentheses = 0;
        int closeParentheses = 0;

        for (char token : expression.toCharArray()) {
            if (Character.isLetter(token)) {
                postfix.append(token);
                continue;
            }

            if (Utils.isOperator(token)) {
                while (!operators.isEmpty() && hasPrecedence(operators.top(), token)) {
                    // Desempilha operadores com precedência maior ou igual
                    postfix.append(operators.pop());
                }

                operators.push(token);
                continue;
            }

            if (token == '(') {
                ++openParentheses;
                operators.push(token);
                continue;
            }

            if (token == ')') {
                ++closeParentheses;

                // Desempilha operadores até encontrar o parêntese de abertura correspondente
                while (!operators.isEmpty() && operators.top() != '(') {
                    postfix.append(operators.pop());
                }

                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("Parêntese de fechamento sem parêntese de abertura correspondente.");
                }

                // Remove o parêntese de abertura correspondente
                operators.pop();
                continue;
            }

            throw new IllegalArgumentException("Operador inválido.");
        }

        if (openParentheses != closeParentheses) {
            throw new IllegalArgumentException("Quantidade incorreta de parênteses.");
        }

        if (postfix.isEmpty() && !expression.isBlank()) {
            throw new IllegalArgumentException("Número inválido de operandos.");
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }

        return postfix.toString();
    }

    public static boolean hasPrecedence(char operator1, char operator2) {
        int priority1 = getPriority(operator1);
        int priority2 = getPriority(operator2);

        return priority1 >= priority2;
    }

    private static int getPriority(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }
}
