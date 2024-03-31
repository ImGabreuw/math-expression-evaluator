import java.util.*;

@SuppressWarnings("resource")
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Stack<Integer> numbers = new Stack<Integer>();
        String arithmeticExpression = "";
        String posfixExpression = "";
        boolean verificationLoop = true;
        Parser parser = new Parser();
        Evaluator evaluator = new Evaluator();
        byte choice;

        while (verificationLoop) {

            System.out.println("""
                    1. Entrada da expressão aritmética na notação infixa.
                    2. Entrada dos valores numéricos associados às variáveis.
                    3. Conversão da expressão, da notação infixa para a notação posfixa, e exibição da expressão
                    convertida para posfixa.
                    4. Avaliação da expressão (apresentação do resultado do cálculo, mostrando a expressão e os
                    valores das variáveis).
                    5. Encerramento do programa.
                    """);

            try {
                System.out.print("Digite uma das opções apresentadas: ");
                choice = scanner.nextByte();

                switch (choice) {
                    case 1:

                        scanner.nextLine(); // Eliminando o buffer de choice

                        System.out.print("Digite uma expressão aritmética de notação infixa: ");
                        arithmeticExpression = scanner.nextLine();
                        System.out.println(arithmeticExpression);
                        break;

                    case 2:

                        if (arithmeticExpression.length() == 0) {
                            throw new IllegalArgumentException("Não foi digitado uma expressão de notação infixa!\n Voltando para o menu...");
                        }

                        char expression[] = arithmeticExpression.toCharArray();

                        for (char c : expression) {

                            if (Character.isLetter(c)) {

                                scanner.nextLine();
                                System.out.printf("Digite um valor para a variável %c : ", c);
                                value = scanner.nextInt();
                                numbers.push(value);
                                break;
                            }
                        }

                        break;

                    case 3:

                        if (arithmeticExpression.length() == 0) {
                            throw new IllegalArgumentException("Não foi digitado uma expressão de notação infixa!\n Voltando para o menu...");
                        }

                        posfixExpression = parser.toPostfixNotion(arithmeticExpression);
                        System.out.println(posfixExpression);

                        break;

                    case 4:

                        if (posfixExpression.length() == 0) {
                            throw new IllegalArgumentException("Não existe uma expressão de notação posfixa!\n Voltando para o menu...");
                        }

                        double result = evaluator.evaluate(posfixExpression, numbers);

                        System.out.println("A expressão é :" + posfixExpression);

                        for(int i = expression.length; i > 0; i--){
                            if(Character.isLetter(expression[i])){
                                System.out.println("Variável" + expression[i] + "possui valor " + numbers.pop());
                            }
                        }

                        System.out.println("O resultado da expressão é " + result);

                        break;

                    case 5:
                        System.out.println("Finalizando o programa....");
                        return;

                    default:
                        System.out.println("Você não digitou uma das opções apresentadas.\nDigite novamente uma nova opção");
                        scanner.nextLine();
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            } catch (java.util.InputMismatchException e) {
                throw new IllegalArgumentException("Você deve digitar uma opção válida.\n Voltando para o menu....");
            }

        }
        scanner.close();
    }
}