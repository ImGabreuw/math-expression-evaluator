import java.util.*;

@SuppressWarnings("resource")
public class Main {

    public static void main(String[] args) {
        List<Double> values = new ArrayList<>();

        String arithmeticExpression = "";
        String postfixExpression = "";

        Parser parser = new Parser();
        Evaluator evaluator = new Evaluator();
        byte choice;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("""
                        
                        1. Entrada da expressão aritmética na notação infixa.
                        2. Entrada dos valores numéricos associados às variáveis.
                        3. Conversão da expressão, da notação infixa para a notação posfixa, e exibição da expressão
                        convertida para posfixa.
                        4. Avaliação da expressão (apresentação do resultado do cálculo, mostrando a expressão e os
                        valores das variáveis).
                        5. Encerramento do programa.
                        """);

                    System.out.print("Digite uma das opções apresentadas: ");
                    choice = scanner.nextByte();
                    scanner.nextLine(); // Consumir a quebra de linha

                    switch (choice) {
                        case 1:
                            System.out.print("Digite uma expressão aritmética de notação infixa: ");
                            arithmeticExpression = scanner.nextLine().trim();
                            break;

                        case 2:
                            if (arithmeticExpression.isEmpty()) {
                                System.out.println("Não foi digitada uma expressão de notação infixa!\nVoltando para o menu...");
                                break;
                            }

                            for (char c : arithmeticExpression.toCharArray()) {
                                if (!Character.isLetter(c)) continue;

                                System.out.printf("Digite um valor para a variável %c: ", c);
                                double value = scanner.nextDouble();
                                values.add(value);
                            }
                            break;

                        case 3:
                            if (arithmeticExpression.isEmpty()) {
                                System.out.println("Não foi digitada uma expressão de notação infixa!\nVoltando para o menu...");
                                break;
                            }

                            postfixExpression = parser.toPostfixNotion(arithmeticExpression);
                            System.out.println(postfixExpression);
                            break;

                        case 4:
                            if (postfixExpression.isEmpty()) {
                                System.out.println("Não existe uma expressão de notação posfixa!\nVoltando para o menu...");
                                break;
                            }

                            double[] valuesIn = new double[values.size()];
                            for (int i = 0; i < values.size(); i++) {
                                valuesIn[i] = values.get(i);
                            }

                            double result = evaluator.evaluate(postfixExpression, valuesIn);

                            System.out.println("A expressão posfixa é: " + postfixExpression);

                            char[] expression = arithmeticExpression.toCharArray();
                            int count = 0;

                            for (char c : expression) {
                                if (Character.isLetter(c)) {
                                    System.out.println("Variável " + c + " possui valor " + valuesIn[count++] + ".");
                                }
                            }

                            System.out.println("O resultado da expressão é " + result + ".");
                            break;

                        case 5:
                            System.out.println("Finalizando o programa....");
                            return;

                        default:
                            System.out.println("Você não digitou uma das opções apresentadas.\nDigite novamente uma nova opção");
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Você deve digitar uma opção válida.\nVoltando para o menu....");
                    scanner.nextLine(); // Consumir a quebra de linha pendente
                }
            }
        } catch (Exception e) {
            System.out.println("Uma erro inesperado acontenceu.");
        }
    }
}