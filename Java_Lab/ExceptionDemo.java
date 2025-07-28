import java.io.*;

public class ExceptionDemo {
    public static void main(String[] args) {
        // Checked Exception
        try {
            FileReader fr = new FileReader("nofile.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Checked Exception: " + e);
        }
        // Unchecked Exception
        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Unchecked Exception: " + e);
        }
    }
}
