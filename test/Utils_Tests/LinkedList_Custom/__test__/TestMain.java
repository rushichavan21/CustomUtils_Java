package Utils_Tests.LinkedList_Custom.__test__;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import DataStructures_Implementations.LinkedList_Custom.Custom_LinkedList;

class TestMain {
    private Custom_LinkedList<Integer> intList;
    private Custom_LinkedList<String> stringList;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        intList = new Custom_LinkedList<>();
        stringList = new Custom_LinkedList<>();

        // Capture System.out for testing print methods
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAddAtStart() {
        intList.addAtStart(10);
        intList.addAtStart(20);
        intList.addAtStart(30);

        assertEquals(3, intList.getSize());
        assertEquals(30, intList.get(0)); // Most recent addition at start
        assertEquals(20, intList.get(1));
        assertEquals(10, intList.get(2));
        assertFalse(intList.isEmpty());
    }

    @Test
    void testAddAtEnd() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);

        assertEquals(3, intList.getSize());
        assertEquals(10, intList.get(0));
        assertEquals(20, intList.get(1));
        assertEquals(30, intList.get(2)); // Most recent addition at end
        assertFalse(intList.isEmpty());
    }

    @Test
    void testAddAtEndEmptyList() {
        assertTrue(intList.isEmpty());
        intList.addAtEnd(100);

        assertEquals(1, intList.getSize());
        assertEquals(100, intList.get(0));
        assertFalse(intList.isEmpty());
    }

    @Test
    void testMixedAddOperations() {
        intList.addAtStart(20);  // [20]
        intList.addAtEnd(30);    // [20, 30]
        intList.addAtStart(10);  // [10, 20, 30]
        intList.addAtEnd(40);    // [10, 20, 30, 40]

        assertEquals(4, intList.getSize());
        assertEquals(10, intList.get(0));
        assertEquals(20, intList.get(1));
        assertEquals(30, intList.get(2));
        assertEquals(40, intList.get(3));
    }

    @Test
    void testRemoveFirst() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);
        assertEquals(3, intList.getSize());

        intList.removeFirst();
        assertEquals(2, intList.getSize());
        assertEquals(20, intList.get(0));
        assertEquals(30, intList.get(1));

        intList.removeFirst();
        assertEquals(1, intList.getSize());
        assertEquals(30, intList.get(0));

        intList.removeFirst();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testRemoveFirstEmptyList() {
        intList.removeFirst();
        String output = outputStream.toString();
        assertTrue(output.contains("Empty List, nothing to delete"));
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testRemoveLast() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);
        assertEquals(3, intList.getSize());

        intList.removeLast();
        assertEquals(2, intList.getSize());
        assertEquals(10, intList.get(0));
        assertEquals(20, intList.get(1));

        intList.removeLast();
        assertEquals(1, intList.getSize());
        assertEquals(10, intList.get(0));

        intList.removeLast();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testRemoveLastEmptyList() {
        intList.removeLast();
        String output = outputStream.toString();
        assertTrue(output.contains("Empty List, nothing to delete"));
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testRemoveLastSingleElement() {
        intList.addAtStart(100);
        assertEquals(1, intList.getSize());

        intList.removeLast();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testGet() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);

        assertEquals(10, intList.get(0));
        assertEquals(20, intList.get(1));
        assertEquals(30, intList.get(2));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(-1));

        intList.addAtEnd(10);
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(-1));
    }

    @Test
    void testSearch() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);

        assertTrue(intList.search(10));
        assertTrue(intList.search(20));
        assertTrue(intList.search(30));
        assertFalse(intList.search(40));
        assertFalse(intList.search(-10));
    }

    @Test
    void testSearchEmptyList() {
        assertFalse(intList.search(10));
        assertFalse(intList.search(null));
    }

    @Test
    void testSearchWithNull() {
        stringList.addAtEnd("apple");
        stringList.addAtEnd(null);
        stringList.addAtEnd("cherry");

        assertTrue(stringList.search("apple"));
        assertTrue(stringList.search(null));
        assertTrue(stringList.search("cherry"));
        assertFalse(stringList.search("banana"));
    }

    @Test
    void testClear() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);
        assertEquals(3, intList.getSize());
        assertFalse(intList.isEmpty());

        intList.clear();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(0));
    }

    @Test
    void testClearEmptyList() {
        intList.clear();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(0, intList.getSize());

        intList.addAtStart(1);
        assertEquals(1, intList.getSize());

        intList.addAtEnd(2);
        assertEquals(2, intList.getSize());

        intList.addAtStart(3);
        assertEquals(3, intList.getSize());

        intList.removeFirst();
        assertEquals(2, intList.getSize());

        intList.removeLast();
        assertEquals(1, intList.getSize());

        intList.clear();
        assertEquals(0, intList.getSize());
    }

    @Test
    void testIsEmpty() {
        assertTrue(intList.isEmpty());

        intList.addAtStart(10);
        assertFalse(intList.isEmpty());

        intList.removeFirst();
        assertTrue(intList.isEmpty());

        intList.addAtEnd(20);
        assertFalse(intList.isEmpty());

        intList.clear();
        assertTrue(intList.isEmpty());
    }

    @Test
    void testPrintListEmpty() {
        intList.printList();
        String output = outputStream.toString().trim();
        assertEquals("List is empty", output);
    }

    @Test
    void testPrintListWithElements() {
        intList.addAtEnd(10);
        intList.addAtEnd(20);
        intList.addAtEnd(30);

        intList.printList();
        String output = outputStream.toString().trim();
        assertEquals("10 -> 20 -> 30 -> null", output);
    }

    @Test
    void testGenericTypes() {
        Custom_LinkedList<Double> doubleList = new Custom_LinkedList<>();

        doubleList.addAtEnd(1.5);
        doubleList.addAtEnd(2.7);
        doubleList.addAtStart(0.5);

        assertEquals(3, doubleList.getSize());
        assertEquals(0.5, doubleList.get(0));
        assertEquals(1.5, doubleList.get(1));
        assertEquals(2.7, doubleList.get(2));

        assertTrue(doubleList.search(1.5));
        assertFalse(doubleList.search(3.14));
    }

    @Test
    void testStringOperations() {
        stringList.addAtEnd("apple");
        stringList.addAtEnd("banana");
        stringList.addAtStart("cherry");

        assertEquals(3, stringList.getSize());
        assertEquals("cherry", stringList.get(0));
        assertEquals("apple", stringList.get(1));
        assertEquals("banana", stringList.get(2));

        assertTrue(stringList.search("apple"));
        assertTrue(stringList.search("banana"));
        assertTrue(stringList.search("cherry"));
        assertFalse(stringList.search("orange"));
    }

    @Test
    void testNullElements() {
        stringList.addAtStart("start");
        stringList.addAtEnd(null);
        stringList.addAtEnd("end");

        assertEquals(3, stringList.getSize());
        assertEquals("start", stringList.get(0));
        assertNull(stringList.get(1));
        assertEquals("end", stringList.get(2));

        assertTrue(stringList.search(null));
        assertTrue(stringList.search("start"));
        assertTrue(stringList.search("end"));
    }

    @Test
    void testLargeList() {
        // Add many elements
        for (int i = 0; i < 100; i++) {
            intList.addAtEnd(i);
        }

        assertEquals(100, intList.getSize());
        assertFalse(intList.isEmpty());

        // Verify all elements
        for (int i = 0; i < 100; i++) {
            assertEquals(i, intList.get(i));
            assertTrue(intList.search(i));
        }

        // Remove from start
        for (int i = 0; i < 50; i++) {
            intList.removeFirst();
        }

        assertEquals(50, intList.getSize());
        assertEquals(50, intList.get(0)); // First element should now be 50
    }

    @Test
    void testMixedOperations() {
        // Complex scenario with various operations
        intList.addAtStart(20);
        intList.addAtEnd(30);
        intList.addAtStart(10);
        assertEquals(3, intList.getSize());

        intList.removeFirst(); // Remove 10
        assertEquals(2, intList.getSize());
        assertEquals(20, intList.get(0));

        intList.addAtEnd(40);
        intList.addAtEnd(50);
        assertEquals(4, intList.getSize());

        intList.removeLast(); // Remove 50
        assertEquals(3, intList.getSize());
        assertEquals(40, intList.get(2));

        intList.clear();
        assertTrue(intList.isEmpty());
    }

    @Test
    void testAlternatingAddRemove() {
        // Test adding and removing alternately
        intList.addAtStart(1);
        assertEquals(1, intList.getSize());

        intList.removeFirst();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());

        intList.addAtEnd(2);
        assertEquals(1, intList.getSize());

        intList.removeLast();
        assertEquals(0, intList.getSize());
        assertTrue(intList.isEmpty());
    }

    @Test
    void testEdgeCasesAfterOperations() {
        // Add elements
        intList.addAtEnd(10);
        intList.addAtEnd(20);

        // Remove all
        intList.removeFirst();
        intList.removeFirst();
        assertTrue(intList.isEmpty());

        // Try operations on now-empty list
        intList.removeFirst(); // Should print message
        intList.removeLast();  // Should print message

        String output = outputStream.toString();
        assertTrue(output.contains("Empty List, nothing to delete"));

        // Should be able to add again
        intList.addAtStart(100);
        assertEquals(1, intList.getSize());
        assertEquals(100, intList.get(0));
    }

    // Cleanup method to restore System.out
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}