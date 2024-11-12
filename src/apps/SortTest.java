package apps;

import adts.SortedList;
import adts.IterationType;

public class SortTest {
    public static void main(String[] args) {
        // Create a SortedList
        SortedList<String> list = new SortedList<>();

        // Test 1: Adding elements and basic operations
        System.out.println("Test 1: Basic Operations");
        System.out.println("------------------------");

        // Add elements to the list (they will be automatically sorted)
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");
        list.add("elderberry");

        // Print list size and contents
        System.out.println("Initial list size: " + list.size());
        System.out.println("Initial list contents: " + list);
        System.out.println();

        // Test 2: Testing different iteration types
        System.out.println("Test 2: Different Iteration Types");
        System.out.println("-------------------------------");

        // Forward iteration
        System.out.println("Forward iteration:");
        list.setIterationType(IterationType.FORWARD);
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println();

        // Backward iteration
        System.out.println("Backward iteration:");
        list.setIterationType(IterationType.BACKWARD);
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println();

        // Random iteration
        System.out.println("Random iteration:");
        list.setIterationType(IterationType.RANDOM);
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println();

        // Test 3: Remove and contains operations
        System.out.println("Test 3: Remove and Contains Operations");
        System.out.println("------------------------------------");

        // Test contains
        System.out.println("Contains 'banana': " + list.contains("banana"));
        System.out.println("Contains 'mango': " + list.contains("mango"));

        // Remove an element
        System.out.println("Removing 'banana': " + list.remove("banana"));
        System.out.println("List after removal: " + list);
        System.out.println("New size: " + list.size());
        System.out.println("Contains 'banana' after removal: " + list.contains("banana"));
        System.out.println();

        // Test 4: Get operations
        System.out.println("Test 4: Get Operations");
        System.out.println("---------------------");

        // Test get by element
        System.out.println("Get 'cherry': " + list.get("cherry"));
        System.out.println("Get 'mango' (non-existent): " + list.get("mango"));

        // Test get by index
        System.out.println("Get index 0: " + list.get(0));
        System.out.println("Get index 2: " + list.get(2));
        System.out.println("Get index 10 (out of bounds): " + list.get(10));
        System.out.println();

        // Test 5: Edge cases
        System.out.println("Test 5: Edge Cases");
        System.out.println("-----------------");

        // Create a new empty list
        SortedList<Integer> numberList = new SortedList<>();

        // Test empty list operations
        System.out.println("Empty list size: " + numberList.size());
        System.out.println("Empty list toString(): " + numberList);
        System.out.println("Empty list contains 5: " + numberList.contains(5));
        System.out.println("Empty list get(0): " + numberList.get(0));

        // Add and remove single element
        numberList.add(5);
        System.out.println("After adding 5: " + numberList);
        numberList.remove(5);
        System.out.println("After removing 5: " + numberList);
        System.out.println("Is empty: " + numberList.isEmpty());
    }
}