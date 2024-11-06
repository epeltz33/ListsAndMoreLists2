package adts;

import interfaces.ListInterface;

public class ArraySortedList<E> extends ArrayBasedList<E> implements ListInterface<E> {

	public ArraySortedList() {
		super();
	}

	public ArraySortedList(int initialCapacity) {
		super(initialCapacity);
	}
	
	@Override
	public void add(E element) {
		
		if (numElements == list.length) {
			enlarge();
		}

		int addLocation = 0;
		
		while (addLocation < numElements) {
			if (((Comparable)element).compareTo(list[addLocation]) > 0) {
				addLocation++;
			}
			else {
				break;
			}
		}
		
		for (int i = numElements; i > addLocation; i--) {  // make room
			list[i] = list[i-1];
		}
		
		list[addLocation] = element;
		
		numElements++;
		
	}
	
	@Override
	protected void find(E target) {
		found = false;
		location  = 0;
		int first = 0;
		int last  = numElements - 1;
		
		while (first <= last) {
			int mid = (first + last) / 2;
			if (target.equals(list[mid])) {
				found = true;
				location = mid;
				return;
			}
			else {
				if (((Comparable)target).compareTo(list[mid]) < 0) {
					last = mid - 1;
				}
				else {
					first = mid + 1;
				}
			}
		}
	}
	
	

}
