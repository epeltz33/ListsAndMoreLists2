package iterators;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {
	
	private E[] list; // a reference to the underlying array for ABL
	private int size; // number of items on the list
	private int current = 0;

	public ArrayIterator(E[] list, int size) {
		this.list = list;
		this.size = size;
	}

	@Override
	public boolean hasNext() {
		return current < size;
	}

	@Override
	public E next() {
		E temp = list[current];
		current++;
		return temp;
	}

}
