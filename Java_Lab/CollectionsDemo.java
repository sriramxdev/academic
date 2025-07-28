import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("A"); al.add("B");
        LinkedList<String> ll = new LinkedList<>();
        ll.add("X"); ll.add("Y");
        HashSet<String> hs = new HashSet<>();
        hs.add("1"); hs.add("2");
        System.out.println("ArrayList: " + al);
        System.out.println("LinkedList: " + ll);
        System.out.println("HashSet: " + hs);
    }
}
