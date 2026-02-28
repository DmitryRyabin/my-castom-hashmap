import java.util.Map;

public class MyCustomHashMap<K, V> {

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public MyCustomHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    }

    private int getIndex(K key) {
        int hash = (key == null) ? 0 : key.hashCode();
        return Math.abs(hash) % table.length;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = table[index];

        Node<K, V> current = head;
        while (current != null) {
            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        table[index] = newNode;
        size++;

        if ((float) size / table.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[oldTable.length * 2];
        size = 0;

        for (Node<K, V> head : oldTable) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];
        Node<K, V> previous = null;

        while (current != null) {

            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {

                if (previous == null) {
                    // удаляем первый элемент списка
                    table[index] = current.next;
                } else {
                    // удаляем не первый
                    previous.next = current.next;
                }

                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }
}
