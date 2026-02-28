import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyCustomHashMap<String, Integer> map = new MyCustomHashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println(map.remove("B")); // 2
        System.out.println(map.get("B"));

        Map<String, Integer> map2 = new HashMap<>();

        map2.put("A", 1);
        map2.put("B", 2);
        map2.put("C", 3);

        System.out.println(map2.remove("B")); // 2
        System.out.println(map2.get("B"));
    }
}
