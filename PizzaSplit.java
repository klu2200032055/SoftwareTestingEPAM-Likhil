package JavaBasic;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of people and slices per pizza
        int people = scanner.nextInt();
        int slicesPerPizza = scanner.nextInt();

        int pizzas = 1;
        while ((pizzas * slicesPerPizza) % people != 0) {
            pizzas++;
        }

        System.out.println(pizzas);
    }
}

