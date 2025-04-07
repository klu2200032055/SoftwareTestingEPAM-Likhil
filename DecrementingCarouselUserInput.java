import java.util.Scanner;

class DecrementingCarousel {
    protected final int[] elements;
    protected int capacity;
    protected boolean isRun;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
        this.isRun = false;
    }

    public boolean addElement(int element) {
        if (isRun || element <= 0) return false;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == 0) {
                elements[i] = element;
                return true;
            }
        }
        return false;
    }

    public CarouselRun run() {
        if (isRun) return null;
        isRun = true;
        return new CarouselRun(elements.clone());
    }
}

class CarouselRun {
    protected final int[] elements;
    protected int position = 0;

    public CarouselRun(int[] elements) {
        this.elements = elements;
    }

    public int next() {
        if (isFinished()) return -1;

        while (elements[position] <= 0) {
            position = (position + 1) % elements.length;
        }

        int value = elements[position];
        elements[position]--;
        position = (position + 1) % elements.length;
        return value;
    }

    public boolean isFinished() {
        for (int el : elements) {
            if (el > 0) return false;
        }
        return true;
    }
}

class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {
    private final int limit;

    public DecrementingCarouselWithLimitedRun(int capacity, int limit) {
        super(capacity);
        this.limit = limit;
    }

    @Override
    public CarouselRun run() {
        if (isRun) return null;
        isRun = true;
        return new LimitedCarouselRun(elements.clone(), limit);
    }

    static class LimitedCarouselRun extends CarouselRun {
        private int remainingCalls;

        public LimitedCarouselRun(int[] elements, int limit) {
            super(elements);
            this.remainingCalls = limit;
        }

        @Override
        public int next() {
            if (isFinished() || remainingCalls <= 0) return -1;
            remainingCalls--;
            return super.next();
        }

        @Override
        public boolean isFinished() {
            return remainingCalls <= 0 || super.isFinished();
        }
    }
}

public class DecrementingCarouselUserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter carousel capacity: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter max operations (limit): ");
        int limit = scanner.nextInt();

        DecrementingCarousel carousel = new DecrementingCarouselWithLimitedRun(capacity, limit);

        System.out.println("Enter elements to add (negative or 0 to stop):");
        while (true) {
            int element = scanner.nextInt();
            if (element <= 0 || !carousel.addElement(element)) break;
        }

        CarouselRun run = carousel.run();

        System.out.println("Carousel output:");
        while (!run.isFinished()) {
            System.out.print(run.next() + " ");
        }
    }
}
