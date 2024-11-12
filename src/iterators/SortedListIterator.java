package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import adts.IterationType;
import nodes.DLLNode;
import adts.SortedList;

public class SortedListIterator<E extends Comparable<E>> implements Iterator<E> {
    private final SortedList<E> list;
    private final IterationType type;
    private DLLNode<E> current;
    private int[] randomIndices;
    private int currentIndex;

    public SortedListIterator(SortedList<E> list, IterationType type) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        this.list = list;
        this.type = type;

        switch (type) {
            case FORWARD:
                current = list.getNode(0);
                break;
            case BACKWARD:
                current = list.getNode(list.size() - 1);
                break;
            case RANDOM:
                initializeRandomOrder();
                if (!list.isEmpty()) {
                    current = list.getNode(randomIndices[0]);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid iteration type");
        }
    }

    private void initializeRandomOrder() {
        int size = list.size();
        randomIndices = new int[size];
        for (int i = 0; i < size; i++) {
            randomIndices[i] = i;
        }

        // Fisher-Yates shuffle
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = randomIndices[i];
            randomIndices[i] = randomIndices[j];
            randomIndices[j] = temp;
        }
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        switch (type) {
            case FORWARD:
            case BACKWARD:
                return current != null;
            case RANDOM:
                return currentIndex < randomIndices.length;
            default:
                return false;
        }
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate");
        }

        E data;
        switch (type) {
            case FORWARD:
                data = current.getData();
                current = current.getNext();
                break;
            case BACKWARD:
                data = current.getData();
                current = current.getPrev();
                break;
            case RANDOM:
                data = current.getData();
                currentIndex++;
                if (currentIndex < randomIndices.length) {
                    current = list.getNode(randomIndices[currentIndex]);
                }
                break;
            default:
                throw new IllegalStateException("Invalid iteration type");
        }

        return data;
    }
}