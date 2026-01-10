public class DoublyLinkedList<K> {

    private DoublyLinkedListNode<K> head;
    private DoublyLinkedListNode<K> tail;

    public void addNodeAtTail(DoublyLinkedListNode<K> node) {
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void detachNode(DoublyLinkedListNode<K> node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
    }

    public DoublyLinkedListNode<K> getHead() {
        return head;
    }

    public void removeHead() {
        if (head == null) return;
        if (head.next != null) {
            head = head.next;
            head.prev = null;
        } else {
            head = tail = null;
        }
    }
}