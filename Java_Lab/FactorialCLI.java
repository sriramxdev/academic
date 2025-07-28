public class FactorialCLI {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FactorialCLI <number>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        System.out.println("Factorial: " + fact);
    }
}
