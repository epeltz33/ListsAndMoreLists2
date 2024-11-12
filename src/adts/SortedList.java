package adts;

import nodes.DLLNode;
import iterators.SortedListIterator;
import interfaces.ListInterface;
import java.util.Iterator;

public class SortedList<E extends Comparable<E>> implements ListInterface<E>, Iterable<E> {
    private DLLNode<E> head;
    private DLLNode<E> tail;
    private int size;
    private IterationType iterationType = IterationType.FORWARD;

    public SortedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null element");
        }

        DLLNode<E> newNode = new DLLNode<>(element);

        // If list is empty
        if (isEmpty()) {
            head = tail = newNode;
            size++;
            return;
        }

        // If element should be at the start
        if (element.compareTo(head.getData()) <= 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            size++;
            return;
        }

        // If element should be at the end
        if (element.compareTo(tail.getData()) >= 0) {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
            size++;
            return;
        }

        // Find insertion point
        DLLNode<E> current = head;
        while (current != null && element.compareTo(current.getData()) > 0) {
            current = current.getNext();
        }

        // Insert before current
        newNode.setNext(current);
        newNode.setPrev(current.getPrev());
        current.getPrev().setNext(newNode);
        current.setPrev(newNode);
        size++;
    }

    @Override
    public boolean remove(E element) {
        if (element == null || isEmpty()) {
            return false;
        }

        DLLNode<E> node = binarySearch(element);
        if (node == null) {
            return false;
        }

        // Remove single node
        if (size == 1) {
            head = tail = null;
        }
        // Remove head
        else if (node == head) {
            head = head.getNext();
            head.setPrev(null);
        }
        // Remove tail
        else if (node == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // Remove from middle
        else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return binarySearch(element) != null;
    }

    @Override
    public E get(E element) {
        DLLNode<E> node = binarySearch(element); // O(log n)
        return node == null ? null : node.getData();
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        DLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    // Helper method for iterator
    public DLLNode<E> getNode(int index) {
        if (index < 0 || index >= size) { // out of bounds check
            return null;
        }

        DLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private DLLNode<E> binarySearch(E element) {
        if (element == null || isEmpty()) {
            return null;
        }

        DLLNode<E> low = head;
        DLLNode<E> high = tail;

        while (low != null && high != null && low.compareTo(high) <= 0) {
            DLLNode<E> mid = getMidNode(low, high);

            if (mid == null) {
                return null;
            }

            int comparison = element.compareTo(mid.getData());

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                if (high == mid) {
                    return null;
                }
                high = mid.getPrev();
            } else {
                if (low == mid) {
                    return null;
                }
                low = mid.getNext();
            }
        }
        return null;
    }

    private DLLNode<E> getMidNode(DLLNode<E> low, DLLNode<E> high) {
        if (low == null || high == null) {
            return null;
        }

        if (low == high) {
            return low;
        }

        DLLNode<E> slow = low;
        DLLNode<E> fast = low;

        while (fast != high && fast.getNext() != high) {
            if (fast.getNext() == null || fast.getNext().getNext() == null) {
                break;
            }
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }

    public void setIterationType(IterationType type) {
        this.iterationType = type;
    }

    @Override
    public Iterator<E> iterator() {
        return new SortedListIterator<>(this, iterationType);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("["); // [1, 2, 3]
        DLLNode<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}