package JavaBasic;

import java.util.Scanner;

public class GoDutch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int billTotal = scanner.nextInt();
        if (billTotal < 0) {
            System.out.println("Bill total amount cannot be negative");
            return;
        }

        int numberOfFriends = scanner.nextInt();
        if (numberOfFriends <= 0) {
            System.out.println("Number of friends cannot be negative or zero");
            return;
        }

        int totalWithTip = (int) Math.round(billTotal * 1.1);
        int partToPay = totalWithTip / numberOfFriends;

        System.out.println(partToPay);
    }
}

