import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for (Integer n : list) {
            int x = n * n;
            System.out.println(x);
        }
        list.stream().map((x) -> x * x).forEach(System.out::println);
        for (Integer n : list) {
            System.out.println(n);
        }
    }

}
