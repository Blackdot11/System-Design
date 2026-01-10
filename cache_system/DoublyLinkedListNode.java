public class DoublyLinkedListNode<K> {
    K value;
    DoublyLinkedListNode<K> prev;
    DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K value) {
        this.value = value;
    }
}