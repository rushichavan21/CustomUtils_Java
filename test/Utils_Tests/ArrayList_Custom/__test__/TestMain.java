package Utils_Tests.ArrayList_Custom.__test__;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures_Implementations.ArrayList_Custom.CustomArrayList;

class TestMain {
    private CustomArrayList<Integer> list;
    private CustomArrayList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new CustomArrayList<>();
        stringList = new CustomArrayList<>();
    }

    @Test
    void testAddAndGet() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertEquals(0, list.size());
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());

        list.add(10);
        assertEquals(1, list.size());

        list.add(20);
        assertEquals(2, list.size());

        list.add(30);
        assertEquals(3, list.size());
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(30, list.remove());
        assertEquals(2, list.size());
        assertEquals(20, list.get(1));

        assertEquals(20, list.remove());
        assertEquals(1, list.size());
        assertEquals(10, list.get(0));

        assertEquals(10, list.remove());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFromEmptyListThrowsException() {
        assertThrows(IllegalStateException.class, () -> list.remove());
        assertEquals(0, list.size());
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testSet() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.set(1, 99);
        assertEquals(99, list.get(1));
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testSetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));

        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 10));
    }

    @Test
    void testContains() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
        assertFalse(list.contains(50));
        assertFalse(list.contains(0));
    }

    @Test
    void testContainsWithStrings() {
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("cherry");

        assertTrue(stringList.contains("apple"));
        assertTrue(stringList.contains("banana"));
        assertFalse(stringList.contains("orange"));
        assertFalse(stringList.contains("Apple")); // case sensitive
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(3, list.size());

        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testIsFull() {
        // Initially not full
        assertFalse(list.isFull());

        // Fill to default capacity (10)
        for (int i = 0; i < 10; i++) {
            list.add(i);
            if (i < 9) {
                assertFalse(list.isFull());
            }
        }
        assertTrue(list.isFull());

        // Adding one more should trigger resize and not be full
        list.add(10);
        assertFalse(list.isFull());
    }

    @Test
    void testAutomaticResize() {
        // Add more than default capacity (10) elements
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        assertEquals(20, list.size());
        // Verify all elements are accessible
        for (int i = 0; i < 20; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void testEnsureCapacity() {
        // Initially has capacity 10
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // Ensure capacity to 20
        list.ensureCapacity(20);

        // Should be able to add more elements without issues
        for (int i = 5; i < 20; i++) {
            list.add(i);
        }

        assertEquals(20, list.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void testEnsureCapacityLessThanCurrent() {
        list.add(10);
        list.add(20);

        // Ensure capacity less than current should not affect anything
        list.ensureCapacity(5);

        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testLargeDataSet() {
        // Test with large number of elements
        int largeSize = 1000;
        for (int i = 0; i < largeSize; i++) {
            list.add(i);
        }

        assertEquals(largeSize, list.size());

        // Verify all elements
        for (int i = 0; i < largeSize; i++) {
            assertEquals(i, list.get(i));
        }

        // Test contains on large dataset
        assertTrue(list.contains(500));
        assertFalse(list.contains(1001));
    }

    @Test
    void testMixedOperations() {
        // Add some elements
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());

        // Remove one
        assertEquals(3, list.remove());
        assertEquals(2, list.size());

        // Set an element
        list.set(0, 10);
        assertEquals(10, list.get(0));

        // Add more elements
        list.add(4);
        list.add(5);
        assertEquals(4, list.size());

        // Clear all
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testGenericTypes() {
        CustomArrayList<Double> doubleList = new CustomArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.7);
        doubleList.add(3.14);

        assertEquals(1.5, doubleList.get(0));
        assertEquals(2.7, doubleList.get(1));
        assertEquals(3.14, doubleList.get(2));
        assertTrue(doubleList.contains(3.14));
        assertFalse(doubleList.contains(4.0));
    }

    @Test
    void testToString() {
        list.add(1);
        list.add(2);
        list.add(3);

        String result = list.toString();
        assertTrue(result.contains("CustomArrayList"));
        assertTrue(result.contains("size=" + list.size()));
    }

    @Test
    void testEmptyListOperations() {
        // Test operations on empty list
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 1));
        assertThrows(IllegalStateException.class, () -> list.remove());

        // Clear empty list should not cause issues
        list.clear();
        assertEquals(0, list.size());
    }
}