package nodes;

public class DLLNode<E> implements Comparable<DLLNode<E>> {
	private E data;
	private DLLNode<E> next;
	private DLLNode<E> prev;

	public DLLNode(E data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public DLLNode<E> getNext() {
		return next;
	}

	public void setNext(DLLNode<E> next) {
		this.next = next;
	}

	public DLLNode<E> getPrev() {
		return prev;
	}

	public void setPrev(DLLNode<E> prev) {
		this.prev = prev;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		DLLNode<?> other = (DLLNode<?>) obj;
		if (data == null) {
			return other.data == null;
		}
		return data.equals(other.data);
	}

	@Override
	public int compareTo(DLLNode<E> other) {
		if (other == null) {
			return 1;
		}
		if (this.data == null) {
			return -1;
		}
		if (other.data == null) {
			return 1;
		}
		return ((Comparable<E>) this.data).compareTo(other.data);
	}

	@Override
	public String toString() {
		return data == null ? "null" : data.toString();
	}
}