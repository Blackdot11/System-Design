import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    private final DoublyLinkedList<K> dll = new DoublyLinkedList<>();
    private final Map<K, DoublyLinkedListNode<K>> map = new HashMap<>();

    public synchronized void keyAccessed(K key) {
        if (map.containsKey(key)) {
            DoublyLinkedListNode<K> node = map.get(key);
            dll.detachNode(node);
            dll.addNodeAtTail(node);
        } else {
            DoublyLinkedListNode<K> node = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(node);
            map.put(key, node);
        }
    }

    public synchronized K evictKey() {
        DoublyLinkedListNode<K> node = dll.getHead();
        if (node == null) return null;
        K key = node.value;
        dll.removeHead();
        map.remove(key);
        return key;
    }
}