package Utils_Tests.Stack_Custom.__test__;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures_Implementations.Stack_Custom.Stack_using_LL;

class TestMain {
    private Stack_using_LL<Integer> intStack;
    private Stack_using_LL<String> stringStack;

    @BeforeEach
    void setUp() {
        intStack = new Stack_using_LL<>();
        stringStack = new Stack_using_LL<>();
    }

    @Test
    void testIsEmptyNewStack() {
        assertTrue(intStack.isEmpty());
        assertTrue(stringStack.isEmpty());
        assertEquals(0, intStack.size());
        assertEquals(0, stringStack.size());
    }

    @Test
    void testIsEmptyAfterOperations() {
        intStack.push(10);
        assertFalse(intStack.isEmpty());
        assertEquals(1, intStack.size());

        intStack.pop();
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());

        intStack.push(20);
        intStack.push(30);
        assertFalse(intStack.isEmpty());
        assertEquals(2, intStack.size());

        intStack.pop();
        intStack.pop();
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
    }

    @Test
    void testPushAndTop() {
        intStack.push(10);
        assertEquals(10, intStack.top());
        assertFalse(intStack.isEmpty());
        assertEquals(1, intStack.size());

        intStack.push(20);
        assertEquals(20, intStack.top()); // Should be last pushed element (top)
        assertEquals(2, intStack.size());

        intStack.push(30);
        assertEquals(30, intStack.top()); // Should be last pushed element (top)
        assertEquals(3, intStack.size());
    }

    @Test
    void testLIFOOrder() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertEquals(30, intStack.pop()); // Last in, first out
        assertEquals(20, intStack.pop());
        assertEquals(10, intStack.pop());
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
    }

    @Test
    void testPop() {
        intStack.push(100);
        intStack.push(200);

        assertEquals(200, intStack.pop());
        assertEquals(100, intStack.top()); // Next element should be 100
        assertFalse(intStack.isEmpty());
        assertEquals(1, intStack.size());

        assertEquals(100, intStack.pop());
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
    }

    @Test
    void testPopEmptyStack() {
        assertNull(intStack.pop());
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());

        // Push and pop, then try popping again
        intStack.push(10);
        assertEquals(10, intStack.pop());
        assertNull(intStack.pop());
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
    }

    @Test
    void testTopEmptyStack() {
        assertNull(intStack.top());

        // Push and pop, then top again
        intStack.push(10);
        assertEquals(10, intStack.top());
        intStack.pop();
        assertNull(intStack.top());
    }

    @Test
    void testTopDoesNotRemove() {
        intStack.push(50);
        intStack.push(60);

        assertEquals(60, intStack.top());
        assertEquals(60, intStack.top()); // Should be same element
        assertEquals(2, intStack.size()); // Size should remain unchanged
        assertEquals(60, intStack.pop()); // Should still be same element
        assertEquals(50, intStack.top()); // Now should be previous element
        assertEquals(1, intStack.size());
    }

    @Test
    void testContains() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertTrue(intStack.contains(10));
        assertTrue(intStack.contains(20));
        assertTrue(intStack.contains(30));
        assertFalse(intStack.contains(40));
        assertFalse(intStack.contains(-10));
    }

    @Test
    void testContainsEmptyStack() {
        assertFalse(intStack.contains(10));
    }

    @Test
    void testContainsAfterPop() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertTrue(intStack.contains(30));
        intStack.pop(); // Remove 30
        assertFalse(intStack.contains(30));
        assertTrue(intStack.contains(20));
        assertTrue(intStack.contains(10));
    }

    @Test
    void testStringStack() {
        stringStack.push("apple");
        stringStack.push("banana");
        stringStack.push("cherry");

        assertEquals("cherry", stringStack.top());
        assertTrue(stringStack.contains("banana"));
        assertFalse(stringStack.contains("orange"));

        assertEquals("cherry", stringStack.pop());
        assertEquals("banana", stringStack.pop());
        assertEquals("apple", stringStack.pop());
        assertTrue(stringStack.isEmpty());
    }

    @Test
    void testSizeOperations() {
        assertEquals(0, intStack.size());

        intStack.push(1);
        assertEquals(1, intStack.size());

        intStack.push(2);
        intStack.push(3);
        assertEquals(3, intStack.size());

        intStack.pop();
        assertEquals(2, intStack.size());

        intStack.pop();
        intStack.pop();
        assertEquals(0, intStack.size());
    }

    @Test
    void testClear() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertFalse(intStack.isEmpty());
        assertEquals(3, intStack.size());

        intStack.clear();

        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
        assertNull(intStack.top());
        assertNull(intStack.pop());
        assertFalse(intStack.contains(10));
        assertFalse(intStack.contains(20));
        assertFalse(intStack.contains(30));
    }

    @Test
    void testClearEmptyStack() {
        assertTrue(intStack.isEmpty());
        intStack.clear();
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
    }

    @Test
    void testToStringEmptyStack() {
        assertEquals("Top -> []", intStack.toString());
        assertEquals("Top -> []", stringStack.toString());
    }

    @Test
    void testToStringSingleElement() {
        intStack.push(42);
        assertEquals("Top -> [42]", intStack.toString());

        stringStack.push("single");
        assertEquals("Top -> [single]", stringStack.toString());
    }

    @Test
    void testToStringMultipleElements() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertEquals("Top -> [30 -> 20 -> 10]", intStack.toString());

        stringStack.push("first");
        stringStack.push("second");
        assertEquals("Top -> [second -> first]", stringStack.toString());
    }

    @Test
    void testMixedOperations() {
        // Push some elements
        intStack.push(1);
        intStack.push(2);
        assertEquals(2, intStack.top());
        assertEquals(2, intStack.size());

        // Pop and push more
        assertEquals(2, intStack.pop());
        intStack.push(3);
        intStack.push(4);
        assertEquals(4, intStack.top());
        assertEquals(3, intStack.size());

        // Verify order
        assertEquals(4, intStack.pop());
        assertEquals(3, intStack.pop());
        assertEquals(1, intStack.pop());
        assertTrue(intStack.isEmpty());
    }

    @Test
    void testGenericTypes() {
        Stack_using_LL<Double> doubleStack = new Stack_using_LL<>();

        doubleStack.push(1.5);
        doubleStack.push(2.7);
        doubleStack.push(3.14);

        assertEquals(3.14, doubleStack.top());
        assertTrue(doubleStack.contains(2.7));
        assertFalse(doubleStack.contains(4.0));
        assertEquals(3, doubleStack.size());

        assertEquals(3.14, doubleStack.pop());
        assertEquals(2.7, doubleStack.pop());
        assertEquals(1.5, doubleStack.pop());
        assertTrue(doubleStack.isEmpty());
    }

    @Test
    void testLargeStack() {
        // Push many elements
        for (int i = 0; i < 100; i++) {
            intStack.push(i);
        }

        assertFalse(intStack.isEmpty());
        assertEquals(100, intStack.size());
        assertEquals(99, intStack.top()); // Last element should be 99

        // Pop all in LIFO order
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, intStack.pop());
        }

        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
        assertNull(intStack.top());
    }

    @Test
    void testAlternatingPushPop() {
        intStack.push(1);
        assertEquals(1, intStack.pop());
        assertTrue(intStack.isEmpty());

        intStack.push(2);
        intStack.push(3);
        assertEquals(3, intStack.pop());

        intStack.push(4);
        assertEquals(4, intStack.pop());
        assertEquals(2, intStack.pop());
        assertTrue(intStack.isEmpty());
    }

    @Test
    void testStackAsBuffer() {
        // Simulate using stack as a temporary buffer
        for (int i = 0; i < 5; i++) {
            intStack.push(i);
        }

        // Process first 3 items (from top)
        for (int i = 4; i >= 2; i--) {
            assertEquals(i, intStack.pop());
        }

        // Add more items
        for (int i = 5; i < 8; i++) {
            intStack.push(i);
        }

        // Verify remaining order: 7, 6, 5, 1, 0 (from top to bottom)
        assertEquals(7, intStack.pop());
        assertEquals(6, intStack.pop());
        assertEquals(5, intStack.pop());
        assertEquals(1, intStack.pop());
        assertEquals(0, intStack.pop());
        assertTrue(intStack.isEmpty());
    }

    @Test
    void testEdgeCaseSingleElementOperations() {
        // Push single element
        intStack.push(100);
        assertFalse(intStack.isEmpty());
        assertEquals(1, intStack.size());
        assertEquals(100, intStack.top());
        assertTrue(intStack.contains(100));
        assertEquals("Top -> [100]", intStack.toString());

        // Pop single element
        assertEquals(100, intStack.pop());
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());
        assertNull(intStack.top());
        assertFalse(intStack.contains(100));
        assertEquals("Top -> []", intStack.toString());
    }

    @Test
    void testHeadPointerIntegrity() {
        // Test that head pointer is maintained correctly

        // Push first element (head should point to it)
        intStack.push(1);
        assertEquals(1, intStack.top());

        // Push second element (head should point to new element)
        intStack.push(2);
        assertEquals(2, intStack.top());

        // Pop first element (head should move to previous)
        assertEquals(2, intStack.pop());
        assertEquals(1, intStack.top());

        // Pop last element (head should be null)
        assertEquals(1, intStack.pop());
        assertTrue(intStack.isEmpty());
        assertNull(intStack.top());
    }

    @Test
    void testContainsWithDuplicates() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(10); // Duplicate
        intStack.push(30);

        assertTrue(intStack.contains(10));
        assertTrue(intStack.contains(20));
        assertTrue(intStack.contains(30));

        // Pop top element (30)
        assertEquals(30, intStack.pop());

        // Pop duplicate 10
        assertEquals(10, intStack.pop());
        assertTrue(intStack.contains(10)); // Should still contain the first 10

        assertEquals(20, intStack.pop());
        assertEquals(10, intStack.pop()); // Pop first 10
        assertFalse(intStack.contains(10)); // Now should not contain 10
    }

    @Test
    void testStringOperationsWithSpaces() {
        stringStack.push("hello world");
        stringStack.push("");
        stringStack.push("  spaces  ");

        assertTrue(stringStack.contains("hello world"));
        assertTrue(stringStack.contains(""));
        assertTrue(stringStack.contains("  spaces  "));
        assertFalse(stringStack.contains("hello"));

        assertEquals("  spaces  ", stringStack.pop());
        assertEquals("", stringStack.pop());
        assertEquals("hello world", stringStack.pop());
        assertTrue(stringStack.isEmpty());
    }

    @Test
    void testToStringAfterOperations() {
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        assertEquals("Top -> [3 -> 2 -> 1]", intStack.toString());

        intStack.pop(); // Remove 3
        assertEquals("Top -> [2 -> 1]", intStack.toString());

        intStack.push(4);
        assertEquals("Top -> [4 -> 2 -> 1]", intStack.toString());

        intStack.pop(); // Remove 4
        intStack.pop(); // Remove 2
        assertEquals("Top -> [1]", intStack.toString());

        intStack.pop(); // Remove 1
        assertEquals("Top -> []", intStack.toString());
    }

    @Test
    void testClearAndReuse() {
        // Use stack, clear it, then use again
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        assertEquals(3, intStack.size());

        intStack.clear();
        assertEquals(0, intStack.size());
        assertTrue(intStack.isEmpty());

        // Reuse after clear
        intStack.push(10);
        intStack.push(20);
        assertEquals(20, intStack.top());
        assertEquals(2, intStack.size());
        assertTrue(intStack.contains(10));
        assertTrue(intStack.contains(20));
    }

    @Test
    void testSizeConsistency() {
        // Verify size is always consistent with actual operations
        assertEquals(0, intStack.size());

        for (int i = 0; i < 10; i++) {
            intStack.push(i);
            assertEquals(i + 1, intStack.size());
        }

        for (int i = 9; i >= 0; i--) {
            assertEquals(i + 1, intStack.size());
            intStack.pop();
        }

        assertEquals(0, intStack.size());
        assertTrue(intStack.isEmpty());
    }

    @Test
    void testContainsNull() {
        // Test behavior when stack contains null values
        Stack_using_LL<String> nullTestStack = new Stack_using_LL<>();

        nullTestStack.push("test");
        nullTestStack.push(null);
        nullTestStack.push("another");

        assertTrue(nullTestStack.contains("test"));
        assertTrue(nullTestStack.contains(null));
        assertTrue(nullTestStack.contains("another"));

        assertEquals("another", nullTestStack.pop());
        assertNull(nullTestStack.pop());
        assertEquals("test", nullTestStack.pop());
    }
}