package Utils_Tests.Queue_Custom.__test__;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures_Implementations.Queue_Custom.Queue_using_LL;

class TestMain {
    private Queue_using_LL<Integer> intQueue;
    private Queue_using_LL<String> stringQueue;

    @BeforeEach
    void setUp() {
        intQueue = new Queue_using_LL<>();
        stringQueue = new Queue_using_LL<>();
    }

    @Test
    void testIsEmptyNewQueue() {
        assertTrue(intQueue.isEmpty());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    void testIsEmptyAfterOperations() {
        intQueue.add(10);
        assertFalse(intQueue.isEmpty());

        intQueue.remove();
        assertTrue(intQueue.isEmpty());

        intQueue.add(20);
        intQueue.add(30);
        assertFalse(intQueue.isEmpty());

        intQueue.remove();
        intQueue.remove();
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testAddAndPeek() {
        intQueue.add(10);
        assertEquals(10, intQueue.peek());
        assertFalse(intQueue.isEmpty());

        intQueue.add(20);
        assertEquals(10, intQueue.peek()); // Should still be first element

        intQueue.add(30);
        assertEquals(10, intQueue.peek()); // Should still be first element
    }

    @Test
    void testFIFOOrder() {
        intQueue.add(10);
        intQueue.add(20);
        intQueue.add(30);

        assertEquals(10, intQueue.remove()); // First in, first out
        assertEquals(20, intQueue.remove());
        assertEquals(30, intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testRemove() {
        intQueue.add(100);
        intQueue.add(200);

        assertEquals(100, intQueue.remove());
        assertEquals(200, intQueue.peek()); // Next element should be 200
        assertFalse(intQueue.isEmpty());

        assertEquals(200, intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testRemoveEmptyQueue() {
        assertNull(intQueue.remove());
        assertTrue(intQueue.isEmpty());

        // Add and remove, then try removing again
        intQueue.add(10);
        assertEquals(10, intQueue.remove());
        assertNull(intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testPeekEmptyQueue() {
        assertNull(intQueue.peek());

        // Add and remove, then peek again
        intQueue.add(10);
        assertEquals(10, intQueue.peek());
        intQueue.remove();
        assertNull(intQueue.peek());
    }

    @Test
    void testPeekDoesNotRemove() {
        intQueue.add(50);
        intQueue.add(60);

        assertEquals(50, intQueue.peek());
        assertEquals(50, intQueue.peek()); // Should be same element
        assertEquals(50, intQueue.remove()); // Should still be same element
        assertEquals(60, intQueue.peek()); // Now should be next element
    }

    @Test
    void testContains() {
        intQueue.add(10);
        intQueue.add(20);
        intQueue.add(30);

        assertTrue(intQueue.contains(10));
        assertTrue(intQueue.contains(20));
        assertTrue(intQueue.contains(30));
        assertFalse(intQueue.contains(40));
        assertFalse(intQueue.contains(-10));
    }

    @Test
    void testContainsEmptyQueue() {
        assertFalse(intQueue.contains(10));
        assertFalse(intQueue.contains(null));
    }

    @Test
    void testContainsAfterRemove() {
        intQueue.add(10);
        intQueue.add(20);
        intQueue.add(30);

        assertTrue(intQueue.contains(10));
        intQueue.remove(); // Remove 10
        assertFalse(intQueue.contains(10));
        assertTrue(intQueue.contains(20));
        assertTrue(intQueue.contains(30));
    }

    @Test
    void testStringQueue() {
        stringQueue.add("apple");
        stringQueue.add("banana");
        stringQueue.add("cherry");

        assertEquals("apple", stringQueue.peek());
        assertTrue(stringQueue.contains("banana"));
        assertFalse(stringQueue.contains("orange"));

        assertEquals("apple", stringQueue.remove());
        assertEquals("banana", stringQueue.remove());
        assertEquals("cherry", stringQueue.remove());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    void testToStringEmptyQueue() {
        assertEquals("[]", intQueue.toString());
        assertEquals("[]", stringQueue.toString());
    }

    @Test
    void testToStringSingleElement() {
        intQueue.add(42);
        assertEquals("[42]", intQueue.toString());

        stringQueue.add("single");
        assertEquals("[single]", stringQueue.toString());
    }

    @Test
    void testToStringMultipleElements() {
        intQueue.add(10);
        intQueue.add(20);
        intQueue.add(30);

        assertEquals("[10, 20, 30]", intQueue.toString());

        stringQueue.add("first");
        stringQueue.add("second");
        assertEquals("[first, second]", stringQueue.toString());
    }

    @Test
    void testMixedOperations() {
        // Add some elements
        intQueue.add(1);
        intQueue.add(2);
        assertEquals(1, intQueue.peek());

        // Remove and add more
        assertEquals(1, intQueue.remove());
        intQueue.add(3);
        intQueue.add(4);
        assertEquals(2, intQueue.peek());

        // Verify order
        assertEquals(2, intQueue.remove());
        assertEquals(3, intQueue.remove());
        assertEquals(4, intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testGenericTypes() {
        Queue_using_LL<Double> doubleQueue = new Queue_using_LL<>();

        doubleQueue.add(1.5);
        doubleQueue.add(2.7);
        doubleQueue.add(3.14);

        assertEquals(1.5, doubleQueue.peek());
        assertTrue(doubleQueue.contains(2.7));
        assertFalse(doubleQueue.contains(4.0));

        assertEquals(1.5, doubleQueue.remove());
        assertEquals(2.7, doubleQueue.remove());
        assertEquals(3.14, doubleQueue.remove());
        assertTrue(doubleQueue.isEmpty());
    }

    @Test
    void testLargeQueue() {
        // Add many elements
        for (int i = 0; i < 100; i++) {
            intQueue.add(i);
        }

        assertFalse(intQueue.isEmpty());
        assertEquals(0, intQueue.peek()); // First element should be 0

        // Remove all in FIFO order
        for (int i = 0; i < 100; i++) {
            assertEquals(i, intQueue.remove());
        }

        assertTrue(intQueue.isEmpty());
        assertNull(intQueue.peek());
    }

    @Test
    void testAlternatingAddRemove() {
        intQueue.add(1);
        assertEquals(1, intQueue.remove());
        assertTrue(intQueue.isEmpty());

        intQueue.add(2);
        intQueue.add(3);
        assertEquals(2, intQueue.remove());

        intQueue.add(4);
        assertEquals(3, intQueue.remove());
        assertEquals(4, intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testQueueAsBuffer() {
        // Simulate using queue as a buffer
        for (int i = 0; i < 5; i++) {
            intQueue.add(i);
        }

        // Process first 3 items
        for (int i = 0; i < 3; i++) {
            assertEquals(i, intQueue.remove());
        }

        // Add more items
        for (int i = 5; i < 8; i++) {
            intQueue.add(i);
        }

        // Verify remaining order: 3, 4, 5, 6, 7
        assertEquals(3, intQueue.remove());
        assertEquals(4, intQueue.remove());
        assertEquals(5, intQueue.remove());
        assertEquals(6, intQueue.remove());
        assertEquals(7, intQueue.remove());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    void testEdgeCaseSingleElementOperations() {
        // Add single element
        intQueue.add(100);
        assertFalse(intQueue.isEmpty());
        assertEquals(100, intQueue.peek());
        assertTrue(intQueue.contains(100));
        assertEquals("[100]", intQueue.toString());

        // Remove single element
        assertEquals(100, intQueue.remove());
        assertTrue(intQueue.isEmpty());
        assertNull(intQueue.peek());
        assertFalse(intQueue.contains(100));
        assertEquals("[]", intQueue.toString());
    }

    @Test
    void testHeadTailPointerIntegrity() {
        // Test that head and tail pointers are maintained correctly

        // Add first element (head and tail should point to same node)
        intQueue.add(1);
        assertEquals(1, intQueue.peek());

        // Add second element (tail should move)
        intQueue.add(2);
        assertEquals(1, intQueue.peek()); // Head should still be 1

        // Remove first element (head should move to second)
        assertEquals(1, intQueue.remove());
        assertEquals(2, intQueue.peek());

        // Remove last element (both head and tail should be null)
        assertEquals(2, intQueue.remove());
        assertTrue(intQueue.isEmpty());
        assertNull(intQueue.peek());
    }

    @Test
    void testContainsWithDuplicates() {
        intQueue.add(10);
        intQueue.add(20);
        intQueue.add(10); // Duplicate
        intQueue.add(30);

        assertTrue(intQueue.contains(10));
        assertTrue(intQueue.contains(20));
        assertTrue(intQueue.contains(30));

        // Remove first 10
        assertEquals(10, intQueue.remove());
        assertTrue(intQueue.contains(10)); // Should still contain the duplicate

        assertEquals(20, intQueue.remove());
        assertEquals(10, intQueue.remove()); // Remove duplicate
        assertFalse(intQueue.contains(10)); // Now should not contain 10
    }

    @Test
    void testStringOperationsWithSpaces() {
        stringQueue.add("hello world");
        stringQueue.add("");
        stringQueue.add("  spaces  ");

        assertTrue(stringQueue.contains("hello world"));
        assertTrue(stringQueue.contains(""));
        assertTrue(stringQueue.contains("  spaces  "));
        assertFalse(stringQueue.contains("hello"));

        assertEquals("hello world", stringQueue.remove());
        assertEquals("", stringQueue.remove());
        assertEquals("  spaces  ", stringQueue.remove());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    void testToStringAfterOperations() {
        intQueue.add(1);
        intQueue.add(2);
        intQueue.add(3);
        assertEquals("[1, 2, 3]", intQueue.toString());

        intQueue.remove(); // Remove 1
        assertEquals("[2, 3]", intQueue.toString());

        intQueue.add(4);
        assertEquals("[2, 3, 4]", intQueue.toString());

        intQueue.remove(); // Remove 2
        intQueue.remove(); // Remove 3
        assertEquals("[4]", intQueue.toString());

        intQueue.remove(); // Remove 4
        assertEquals("[]", intQueue.toString());
    }
}