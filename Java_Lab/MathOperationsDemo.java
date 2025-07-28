class MathOperations {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}

public class MathOperationsDemo {
    public static void main(String[] args) {
        MathOperations mo = new MathOperations();
        System.out.println(mo.add(2, 3));
        System.out.println(mo.add(2.5, 3.5));
        System.out.println(mo.add(1, 2, 3));
    }
}
