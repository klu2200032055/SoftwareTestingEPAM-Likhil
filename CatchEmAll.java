package JavaBasic;

import java.io.*;

public class CatchEmAll {
    public static void main(String[] args) {
        try {
            riskyMethod();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Resource is missing", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Resource error", e);
        } catch (ArithmeticException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        // Other exceptions are not caught intentionally.
    }

    // Simulates various exceptions (customize to test)
    public static void riskyMethod() throws IOException {
        // Example test: Uncomment one at a time to simulate
        // throw new FileNotFoundException("File not found during read");
        // throw new IOException("General I/O error");
        // throw new ArithmeticException("Divide by zero");
        // throw new NumberFormatException("Invalid number format");
        // throw new IllegalStateException("Unknown state"); // should NOT be caught

        System.out.println("riskyMethod executed without exception.");
    }
}
