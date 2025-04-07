package JavaBasic;

import java.util.Scanner;

public class HalvingCarouselDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter carousel capacity: ");
        int capacity = scanner.nextInt();
        HalvingCarousel carousel = new HalvingCarousel(capacity);

        System.out.print("Enter number of elements to add: ");
        int count = scanner.nextInt();
        System.out.println("Enter element values:");
        for (int i = 0; i < count; i++) {
            int value = scanner.nextInt();
            if (!carousel.addElement(value)) {
                System.out.println("Failed to add " + value + " (invalid or carousel full)");
            }
        }

        CarouselRun run = carousel.run();
        System.out.println("Running carousel:");
        while (!run.isFinished()) {
            System.out.print(run.next() + " ");
        }
        System.out.println("\nCarousel finished. Next: " + run.next());
    }

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
            for (int e : elements) {
                if (e > 0) return false;
            }
            return true;
        }
    }

    static class HalvingCarousel extends DecrementingCarousel {
        public HalvingCarousel(int capacity) {
            super(capacity);
        }

        @Override
        public CarouselRun run() {
            if (isRun) return null;
            isRun = true;
            return new HalvingRun(elements.clone());
        }

        private static class HalvingRun extends CarouselRun {
            public HalvingRun(int[] elements) {
                super(elements);
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
                elements[position] /= 2;
                position = (position + 1) % elements.length;
                return current;
            }
        }
    }
}

