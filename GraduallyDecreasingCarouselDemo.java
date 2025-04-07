package JavaBasic;

import java.util.Scanner;

public class GraduallyDecreasingCarouselDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter carousel capacity: ");
        int capacity = scanner.nextInt();
        GraduallyDecreasingCarousel carousel = new GraduallyDecreasingCarousel(capacity);

        System.out.print("Enter number of elements to add: ");
        int n = scanner.nextInt();
        System.out.println("Enter element values (positive integers):");
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            if (!carousel.addElement(val)) {
                System.out.println("Failed to add " + val + " (maybe carousel full or value invalid)");
            }
        }

        CarouselRun run = carousel.run();
        System.out.println("Running carousel:");
        while (!run.isFinished()) {
            System.out.print(run.next() + " ");
        }
        System.out.println("\nCarousel finished. Next call returns: " + run.next());
    }

    // ========== Base Class ==========
    static class DecrementingCarousel {
        protected final int[] elements;
        protected int count = 0;
        protected boolean isRun = false;

        public DecrementingCarousel(int capacity) {
            elements = new int[capacity];
        }

        public boolean addElement(int element) {
            if (isRun || count >= elements.length || element <= 0) return false;
            elements[count++] = element;
            return true;
        }

        public CarouselRun run() {
            if (isRun) return null;
            isRun = true;
            return new CarouselRun(elements.clone());
        }
    }

    // ========== Carousel Run ==========
    static class CarouselRun {
        protected final int[] elements;
        protected int position = 0;

        public CarouselRun(int[] elements) {
            this.elements = elements;
        }

        public int next() {
            if (isFinished()) return -1;

            int cycles = 0;
            while (elements[position] <= 0 && cycles < elements.length) {
                position = (position + 1) % elements.length;
                cycles++;
            }

            if (elements[position] <= 0) return -1;

            int current = elements[position];
            elements[position]--;
            position = (position + 1) % elements.length;
            return current;
        }

        public boolean isFinished() {
            for (int el : elements) {
                if (el > 0) return false;
            }
            return true;
        }
    }

    // ========== Gradually Decreasing ==========
    static class GraduallyDecreasingCarousel extends DecrementingCarousel {
        public GraduallyDecreasingCarousel(int capacity) {
            super(capacity);
        }

        @Override
        public CarouselRun run() {
            if (isRun) return null;
            isRun = true;
            return new GraduallyDecreasingRun(elements.clone());
        }

        private static class GraduallyDecreasingRun extends CarouselRun {
            private final int[] decrementLevels;

            public GraduallyDecreasingRun(int[] elements) {
                super(elements);
                decrementLevels = new int[elements.length];
            }

            @Override
            public int next() {
                if (isFinished()) return -1;

                int cycles = 0;
                while (elements[position] <= 0 && cycles < elements.length) {
                    position = (position + 1) % elements.length;
                    cycles++;
                }

                if (elements[position] <= 0) return -1;

                int current = elements[position];
                decrementLevels[position]++;
                elements[position] -= decrementLevels[position];
                if (elements[position] < 0) elements[position] = 0;

                position = (position + 1) % elements.length;
                return current;
            }
        }
    }
}
