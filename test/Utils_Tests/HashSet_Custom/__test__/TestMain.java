package Utils_Tests.HashSet_Custom.__test__;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures_Implementations.HashSet_Custom.Custom_HashSet;

class TestMain {
    private Custom_HashSet<String> stringSet;
    private Custom_HashSet<Integer> intSet;

    @BeforeEach
    void setUp() {
        stringSet = new Custom_HashSet<>();
        intSet = new Custom_HashSet<>();
    }

    @Test
    void testAdd() {
        assertTrue(stringSet.add("apple"));
        assertTrue(stringSet.add("banana"));
        assertTrue(stringSet.add("cherry"));

        assertEquals(3, stringSet.size());
        assertTrue(stringSet.contains("apple"));
        assertTrue(stringSet.contains("banana"));
        assertTrue(stringSet.contains("cherry"));
    }

    @Test
    void testAddDuplicate() {
        assertTrue(stringSet.add("duplicate"));
        assertEquals(1, stringSet.size());

        // Adding same element should return false
        assertFalse(stringSet.add("duplicate"));
        assertEquals(1, stringSet.size());
        assertTrue(stringSet.contains("duplicate"));
    }

    @Test
    void testAddNull() {
        assertTrue(stringSet.add(null));
        assertEquals(1, stringSet.size());
        assertTrue(stringSet.contains(null));

        // Adding null again should return false
        assertFalse(stringSet.add(null));
        assertEquals(1, stringSet.size());
    }

    @Test
    void testRemove() {
        stringSet.add("remove1");
        stringSet.add("remove2");
        stringSet.add("keep");
        assertEquals(3, stringSet.size());

        assertTrue(stringSet.remove("remove1"));
        assertEquals(2, stringSet.size());
        assertFalse(stringSet.contains("remove1"));
        assertTrue(stringSet.contains("remove2"));
        assertTrue(stringSet.contains("keep"));

        assertTrue(stringSet.remove("remove2"));
        assertEquals(1, stringSet.size());
        assertFalse(stringSet.contains("remove2"));
        assertTrue(stringSet.contains("keep"));
    }

    @Test
    void testRemoveNonExistent() {
        assertFalse(stringSet.remove("nonexistent"));
        assertEquals(0, stringSet.size());

        stringSet.add("existing");
        assertFalse(stringSet.remove("nonexistent"));
        assertEquals(1, stringSet.size());
        assertTrue(stringSet.contains("existing"));
    }

    @Test
    void testRemoveNull() {
        stringSet.add(null);
        stringSet.add("regular");
        assertEquals(2, stringSet.size());

        assertTrue(stringSet.remove(null));
        assertEquals(1, stringSet.size());
        assertFalse(stringSet.contains(null));
        assertTrue(stringSet.contains("regular"));
    }

    @Test
    void testContains() {
        assertFalse(stringSet.contains("test"));

        stringSet.add("test");
        assertTrue(stringSet.contains("test"));
        assertFalse(stringSet.contains("other"));

        stringSet.add(null);
        assertTrue(stringSet.contains(null));
        assertFalse(stringSet.contains("null")); // String "null" is different from null
    }

    @Test
    void testSize() {
        assertEquals(0, stringSet.size());

        stringSet.add("first");
        assertEquals(1, stringSet.size());

        stringSet.add("second");
        assertEquals(2, stringSet.size());

        stringSet.add("first"); // Duplicate
        assertEquals(2, stringSet.size());

        stringSet.remove("first");
        assertEquals(1, stringSet.size());

        stringSet.remove("second");
        assertEquals(0, stringSet.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(stringSet.isEmpty());

        stringSet.add("test");
        assertFalse(stringSet.isEmpty());

        stringSet.remove("test");
        assertTrue(stringSet.isEmpty());

        stringSet.add(null);
        assertFalse(stringSet.isEmpty());
    }

    @Test
    void testClear() {
        stringSet.add("item1");
        stringSet.add("item2");
        stringSet.add("item3");
        stringSet.add(null);
        assertEquals(4, stringSet.size());
        assertFalse(stringSet.isEmpty());

        stringSet.clear();
        assertEquals(0, stringSet.size());
        assertTrue(stringSet.isEmpty());
        assertFalse(stringSet.contains("item1"));
        assertFalse(stringSet.contains(null));
    }

    @Test
    void testHashCollisions() {
        // Using integers that might hash to same bucket
        intSet.add(1);
        intSet.add(17); // 17 % 16 = 1, same as 1 % 16 = 1
        intSet.add(33); // 33 % 16 = 1
        intSet.add(49); // 49 % 16 = 1

        assertEquals(4, intSet.size());
        assertTrue(intSet.contains(1));
        assertTrue(intSet.contains(17));
        assertTrue(intSet.contains(33));
        assertTrue(intSet.contains(49));

        // Remove from middle of chain
        assertTrue(intSet.remove(17));
        assertEquals(3, intSet.size());
        assertFalse(intSet.contains(17));
        assertTrue(intSet.contains(1));
        assertTrue(intSet.contains(33));
        assertTrue(intSet.contains(49));
    }

    @Test
    void testAutomaticResize() {
        // Default capacity is 16, load factor is 0.7
        // Resize should happen when size > 16 * 0.7 = 11.2, i.e., at size 12

        // Add 15 elements to trigger resize
        for (int i = 0; i < 15; i++) {
            assertTrue(intSet.add(i));
        }

        assertEquals(15, intSet.size());

        // Verify all elements are still accessible after resize
        for (int i = 0; i < 15; i++) {
            assertTrue(intSet.contains(i));
        }

        // Add more elements
        for (int i = 15; i < 20; i++) {
            assertTrue(intSet.add(i));
        }

        assertEquals(20, intSet.size());
        for (int i = 0; i < 20; i++) {
            assertTrue(intSet.contains(i));
        }
    }

    @Test
    void testLargeDataSet() {
        int largeSize = 1000;

        // Add large number of elements
        for (int i = 0; i < largeSize; i++) {
            assertTrue(intSet.add(i));
        }

        assertEquals(largeSize, intSet.size());

        // Verify all elements exist
        for (int i = 0; i < largeSize; i++) {
            assertTrue(intSet.contains(i));
        }

        // Try adding duplicates
        for (int i = 0; i < 100; i++) {
            assertFalse(intSet.add(i)); // Should return false
        }
        assertEquals(largeSize, intSet.size()); // Size shouldn't change

        // Remove half the elements
        for (int i = 0; i < largeSize / 2; i++) {
            assertTrue(intSet.remove(i));
        }

        assertEquals(largeSize / 2, intSet.size());

        // Verify removed elements are gone and remaining exist
        for (int i = 0; i < largeSize / 2; i++) {
            assertFalse(intSet.contains(i));
        }
        for (int i = largeSize / 2; i < largeSize; i++) {
            assertTrue(intSet.contains(i));
        }
    }

    @Test
    void testGenericTypes() {
        Custom_HashSet<Double> doubleSet = new Custom_HashSet<>();

        assertTrue(doubleSet.add(1.5));
        assertTrue(doubleSet.add(2.7));
        assertTrue(doubleSet.add(3.14));
        assertFalse(doubleSet.add(1.5)); // Duplicate

        assertEquals(3, doubleSet.size());
        assertTrue(doubleSet.contains(1.5));
        assertTrue(doubleSet.contains(2.7));
        assertTrue(doubleSet.contains(3.14));
        assertFalse(doubleSet.contains(4.0));
    }

    @Test
    void testMixedOperations() {
        // Complex scenario with mixed operations
        assertTrue(stringSet.add("a"));
        assertTrue(stringSet.add("b"));
        assertTrue(stringSet.add("c"));
        assertEquals(3, stringSet.size());

        assertFalse(stringSet.add("a")); // Duplicate
        assertEquals(3, stringSet.size());

        assertTrue(stringSet.remove("b"));
        assertEquals(2, stringSet.size());
        assertFalse(stringSet.contains("b"));

        assertTrue(stringSet.add("d"));
        assertEquals(3, stringSet.size());
        assertTrue(stringSet.contains("d"));

        stringSet.clear();
        assertTrue(stringSet.isEmpty());
        assertEquals(0, stringSet.size());
    }

    @Test
    void testToString() {
        // Empty set
        assertEquals("[]", stringSet.toString());

        // Single element
        stringSet.add("single");
        String result = stringSet.toString();
        assertTrue(result.contains("single"));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));

        // Multiple elements
        stringSet.add("another");
        result = stringSet.toString();
        assertTrue(result.contains("single"));
        assertTrue(result.contains("another"));
        // Note: Order might vary due to hashing
    }

    @Test
    void testNullHandling() {
        // Add null
        assertTrue(stringSet.add(null));
        assertTrue(stringSet.contains(null));
        assertEquals(1, stringSet.size());

        // Add regular elements with null
        stringSet.add("regular1");
        stringSet.add("regular2");
        assertEquals(3, stringSet.size());
        assertTrue(stringSet.contains(null));
        assertTrue(stringSet.contains("regular1"));
        assertTrue(stringSet.contains("regular2"));

        // Remove null
        assertTrue(stringSet.remove(null));
        assertFalse(stringSet.contains(null));
        assertEquals(2, stringSet.size());

        // Verify regular elements still exist
        assertTrue(stringSet.contains("regular1"));
        assertTrue(stringSet.contains("regular2"));
    }

    @Test
    void testChainOperations() {
        // Test operations that manipulate linked list chains
        intSet.add(1);
        intSet.add(17); // Same bucket as 1
        intSet.add(33); // Same bucket as 1 and 17

        // Remove head of chain
        assertTrue(intSet.remove(33)); // Assuming this is at head
        assertTrue(intSet.contains(1));
        assertTrue(intSet.contains(17));
        assertFalse(intSet.contains(33));

        // Remove from middle/end
        assertTrue(intSet.remove(1));
        assertTrue(intSet.contains(17));
        assertFalse(intSet.contains(1));

        // Remove last in chain
        assertTrue(intSet.remove(17));
        assertFalse(intSet.contains(17));
        assertTrue(intSet.isEmpty());
    }

    @Test
    void testStringVariations() {
        stringSet.add("apple");
        stringSet.add("Apple"); // Different case
        stringSet.add("APPLE"); // All caps
        stringSet.add(""); // Empty string
        stringSet.add(" "); // Space

        assertEquals(5, stringSet.size());
        assertTrue(stringSet.contains("apple"));
        assertTrue(stringSet.contains("Apple"));
        assertTrue(stringSet.contains("APPLE"));
        assertTrue(stringSet.contains(""));
        assertTrue(stringSet.contains(" "));
        assertFalse(stringSet.contains("orange"));
    }

    @Test
    void testEdgeCasesWithEmptySet() {
        // Operations on empty set
        assertFalse(stringSet.contains("anything"));
        assertFalse(stringSet.remove("anything"));
        assertEquals(0, stringSet.size());
        assertTrue(stringSet.isEmpty());
        assertEquals("[]", stringSet.toString());

        // Clear empty set
        stringSet.clear();
        assertTrue(stringSet.isEmpty());
        assertEquals(0, stringSet.size());
    }

    @Test
    void testResizePreservesAllElements() {
        // Add elements that will force multiple resizes
        for (int i = 0; i < 50; i++) {
            assertTrue(intSet.add(i));
        }

        assertEquals(50, intSet.size());

        // Verify all elements are still there after resizes
        for (int i = 0; i < 50; i++) {
            assertTrue(intSet.contains(i));
        }

        // Remove some elements
        for (int i = 0; i < 25; i++) {
            assertTrue(intSet.remove(i));
        }

        assertEquals(25, intSet.size());

        // Verify correct elements remain
        for (int i = 0; i < 25; i++) {
            assertFalse(intSet.contains(i));
        }
        for (int i = 25; i < 50; i++) {
            assertTrue(intSet.contains(i));
        }
    }
}