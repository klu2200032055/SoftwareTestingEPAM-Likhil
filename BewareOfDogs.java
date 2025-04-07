package JavaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BewareOfDogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        House<Dog> dogHouse = new House<>();
        House<Cat> catHouse = new House<>();

        System.out.println("Enter animal and house (e.g., Dog dog, Cat cat), type 'done' to finish:");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("done")) break;

            switch (input) {
                case "dog dog":
                    dogHouse.enter(new Dog());
                    break;
                case "cat cat":
                    catHouse.enter(new Cat());
                    break;
                case "dog cat":
                case "cat dog":
                    System.out.println("Error: Cannot enter wrong house!");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        System.out.println("\n--- Residents ---");
        System.out.println("Dog House: " + dogHouse.getResidents().size() + " dog(s)");
        System.out.println("Cat House: " + catHouse.getResidents().size() + " cat(s)");
        scanner.close();
    }

    interface Animal {
        void makeSound();
    }

    static class Dog implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Woof!");
        }
    }

    static class Cat implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Meow!");
        }
    }

    static class House<T extends Animal> {
        private final List<T> residents = new ArrayList<>();

        public void enter(T animal) {
            residents.add(animal);
            System.out.println(animal.getClass().getSimpleName() + " has entered the house.");
        }

        public List<T> getResidents() {
            return residents;
        }
    }
}
