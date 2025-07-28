import java.util.*;

public class LambdaDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);
    }
}
