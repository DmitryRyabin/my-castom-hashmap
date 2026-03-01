import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCustomHashMapTest {

    private MyCustomHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyCustomHashMap<>();
    }

    @Test
    void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertNull(map.get("three"));
    }

    @Test
    void testOverwriteValue() {
        map.put("key", 10);
        assertEquals(10, map.get("key"));

        map.put("key", 20); // перезаписываем
        assertEquals(20, map.get("key"));
    }

    @Test
    void testRemove() {
        map.put("a", 100);
        map.put("b", 200);

        assertEquals(100, map.remove("a"));
        assertNull(map.get("a"));
        assertEquals(200, map.get("b"));

        // удаление несуществующего
        assertNull(map.remove("c"));
    }

    @Test
    void testNullKey() {
        map.put(null, 50);
        assertEquals(50, map.get(null));

        map.put(null, 100); // обновляем null ключ
        assertEquals(100, map.get(null));

        assertEquals(100, map.remove(null));
        assertNull(map.get(null));
    }

    @Test
    void testResize() {
        // Добавляем много элементов чтобы вызвать resize
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals(i, map.get("key" + i));
        }

        // проверяем что старые элементы не потерялись после resize
        assertEquals(0, map.get("key0"));
        assertEquals(19, map.get("key19"));
    }

    @Test
    void testCollisionHandling() {
        // Пытаемся вызвать коллизию (можно использовать специальные ключи, но здесь простой пример)
        map.put("Aa", 1);
        map.put("BB", 2); // "Aa" и "BB" имеют одинаковый hashCode в Java 8
        assertEquals(1, map.get("Aa"));
        assertEquals(2, map.get("BB"));
    }
}