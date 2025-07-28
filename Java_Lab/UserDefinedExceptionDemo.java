class MyException extends Exception {
    MyException(String msg) { super(msg); }
}

public class UserDefinedExceptionDemo {
    public static void main(String[] args) {
        try {
            int x = 5;
            if (x < 10) throw new MyException("x is less than 10");
            System.out.println("x is " + x);
        } catch (MyException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }
}
