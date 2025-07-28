public class SecondLargestCLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Provide at least two numbers.");
            return;
        }
        int[] arr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }
        if (second == Integer.MIN_VALUE) {
            System.out.println("No second largest number.");
        } else {
            System.out.println("Second largest: " + second);
        }
    }
}
