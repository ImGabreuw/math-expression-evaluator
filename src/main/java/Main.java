import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> numbers = new StaticStack<>();

        System.out.println("""
                1. Entrada da expressão aritmética na notação infixa.
                2. Entrada dos valores numéricos associados às variáveis.
                3. Conversão da expressão, da notação infixa para a notação posfixa, e exibição da expressão
                convertida para posfixa.
                4. Avaliação da expressão (apresentação do resultado do cálculo, mostrando a expressão e os
                valores das variáveis).
                5. Encerramento do programa.
                """);

    }

}
