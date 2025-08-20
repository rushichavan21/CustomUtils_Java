package Utils_Tests.HashMap_Custom.__test__;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures_Implementations.HashMap_Custom.Custom_HashMap;

class TestMain {
    private Custom_HashMap<String, Integer> map;
    private Custom_HashMap<Integer, String> intMap;

    @BeforeEach
    void setUp() {
        map = new Custom_HashMap<>();
        intMap = new Custom_HashMap<>();
    }

    @Test
    void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
        assertEquals(3, map.size());
    }

    @Test
    void testPutUpdateValue() {
        map.put("key", 10);
        assertEquals(10, map.get("key"));
        assertEquals(1, map.size());

        // Update existing key
        map.put("key", 20);
        assertEquals(20, map.get("key"));
        assertEquals(1, map.size()); // Size should remain same
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(map.get("nonexistent"));

        map.put("existing", 100);
        assertNull(map.get("nonexistent"));
        assertEquals(100, map.get("existing"));
    }

    @Test
    void testNullKey() {
        map.put(null, 999);
        assertEquals(999, map.get(null));
        assertEquals(1, map.size());
        assertTrue(map.containsKey(null));

        // Update null key
        map.put(null, 888);
        assertEquals(888, map.get(null));
        assertEquals(1, map.size());
    }

    @Test
    void testNullValue() {
        map.put("nullValue", null);
        assertNull(map.get("nullValue"));
        assertTrue(map.containsKey("nullValue"));
        assertEquals(1, map.size());
    }

    @Test
    void testRemove() {
        map.put("remove1", 100);
        map.put("remove2", 200);
        map.put("keep", 300);

        assertEquals(100, map.remove("remove1"));
        assertNull(map.get("remove1"));
        assertFalse(map.containsKey("remove1"));
        assertEquals(2, map.size());

        assertEquals(200, map.remove("remove2"));
        assertEquals(1, map.size());
        assertEquals(300, map.get("keep"));
    }

    @Test
    void testRemoveNonExistentKey() {
        assertNull(map.remove("nonexistent"));
        assertEquals(0, map.size());

        map.put("existing", 100);
        assertNull(map.remove("nonexistent"));
        assertEquals(1, map.size());
    }

    @Test
    void testRemoveNullKey() {
        map.put(null, 500);
        map.put("regular", 600);

        assertEquals(500, map.remove(null));
        assertNull(map.get(null));
        assertFalse(map.containsKey(null));
        assertEquals(1, map.size());
        assertEquals(600, map.get("regular"));
    }

    @Test
    void testContainsKey() {
        assertFalse(map.containsKey("test"));

        map.put("test", 123);
        assertTrue(map.containsKey("test"));
        assertFalse(map.containsKey("other"));

        map.put(null, 456);
        assertTrue(map.containsKey(null));
    }

    @Test
    void testSize() {
        assertEquals(0, map.size());

        map.put("first", 1);
        assertEquals(1, map.size());

        map.put("second", 2);
        assertEquals(2, map.size());

        map.put("first", 10); // Update existing
        assertEquals(2, map.size());

        map.remove("first");
        assertEquals(1, map.size());

        map.remove("second");
        assertEquals(0, map.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(map.isEmpty());

        map.put("test", 1);
        assertFalse(map.isEmpty());

        map.remove("test");
        assertTrue(map.isEmpty());
    }

    @Test
    void testClear() {
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());

        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("key1"));
        assertFalse(map.containsKey("key1"));
    }

    @Test
    void testHashCollisions() {
        // Create custom objects that will likely hash to same bucket
        intMap.put(1, "one");
        intMap.put(17, "seventeen"); // 17 % 16 = 1, same as 1 % 16 = 1
        intMap.put(33, "thirty-three"); // 33 % 16 = 1

        assertEquals("one", intMap.get(1));
        assertEquals("seventeen", intMap.get(17));
        assertEquals("thirty-three", intMap.get(33));
        assertEquals(3, intMap.size());

        // Remove middle element in chain
        assertEquals("seventeen", intMap.remove(17));
        assertEquals("one", intMap.get(1));
        assertEquals("thirty-three", intMap.get(33));
        assertNull(intMap.get(17));
        assertEquals(2, intMap.size());
    }

    @Test
    void testAutomaticResize() {
        // Default capacity is 16, load factor is 0.7
        // So resize should happen when size > 16 * 0.7 = 11.2, i.e., at size 12

        // Add 15 elements to trigger resize
        for (int i = 0; i < 15; i++) {
            intMap.put(i, "value" + i);
        }

        assertEquals(15, intMap.size());

        // Verify all elements are still accessible after resize
        for (int i = 0; i < 15; i++) {
            assertEquals("value" + i, intMap.get(i));
            assertTrue(intMap.containsKey(i));
        }
    }

    @Test
    void testLargeDataSet() {
        int largeSize = 1000;

        // Put large number of elements
        for (int i = 0; i < largeSize; i++) {
            intMap.put(i, "value" + i);
        }

        assertEquals(largeSize, intMap.size());

        // Verify all elements
        for (int i = 0; i < largeSize; i++) {
            assertEquals("value" + i, intMap.get(i));
            assertTrue(intMap.containsKey(i));
        }

        // Remove half the elements
        for (int i = 0; i < largeSize / 2; i++) {
            assertEquals("value" + i, intMap.remove(i));
        }

        assertEquals(largeSize / 2, intMap.size());

        // Verify remaining elements
        for (int i = largeSize / 2; i < largeSize; i++) {
            assertEquals("value" + i, intMap.get(i));
        }
    }

    @Test
    void testMixedOperations() {
        // Complex scenario with mixed operations
        map.put("a", 1);
        map.put("b", 2);
        assertEquals(2, map.size());

        map.put("a", 10); // Update
        assertEquals(10, map.get("a"));
        assertEquals(2, map.size());

        map.put("c", 3);
        assertTrue(map.containsKey("c"));
        assertEquals(3, map.size());

        assertEquals(2, map.remove("b"));
        assertFalse(map.containsKey("b"));
        assertEquals(2, map.size());

        map.clear();
        assertTrue(map.isEmpty());
        assertNull(map.get("a"));
    }

    @Test
    void testGenericTypes() {
        Custom_HashMap<Double, Boolean> doubleMap = new Custom_HashMap<>();

        doubleMap.put(1.5, true);
        doubleMap.put(2.7, false);
        doubleMap.put(3.14, true);

        assertEquals(true, doubleMap.get(1.5));
        assertEquals(false, doubleMap.get(2.7));
        assertEquals(true, doubleMap.get(3.14));
        assertTrue(doubleMap.containsKey(3.14));
        assertFalse(doubleMap.containsKey(4.0));
        assertEquals(3, doubleMap.size());
    }

    @Test
    void testToString() {
        // Empty map
        assertEquals("{}", map.toString());

        // Single element
        map.put("key", 100);
        String result = map.toString();
        assertTrue(result.contains("key=100"));
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));

        // Multiple elements
        map.put("another", 200);
        result = map.toString();
        assertTrue(result.contains("key=100"));
        assertTrue(result.contains("another=200"));
        assertTrue(result.contains(","));
    }

    @Test
    void testNullKeyAndValueCombinations() {
        // Null key with non-null value
        map.put(null, 100);
        assertEquals(100, map.get(null));

        // Non-null key with null value
        map.put("key", null);
        assertNull(map.get("key"));
        assertTrue(map.containsKey("key"));

        // Null key with null value
        map.put(null, null);
        assertNull(map.get(null));
        assertTrue(map.containsKey(null));

        assertEquals(2, map.size()); // null key was updated, "key" is separate
    }

    @Test
    void testChainedOperations() {
        // Test operations that might affect linked list structure
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);

        // Remove first element
        assertEquals(1, map.remove("first"));
        assertEquals(2, map.get("second"));
        assertEquals(3, map.get("third"));

        // Remove last element
        assertEquals(3, map.remove("third"));
        assertEquals(2, map.get("second"));
        assertNull(map.get("third"));

        // Remove middle element (if it was middle)
        assertEquals(2, map.remove("second"));
        assertTrue(map.isEmpty());
    }

    @Test
    void testEdgeCasesWithEmptyMap() {
        // Operations on empty map
        assertNull(map.get("anything"));
        assertNull(map.remove("anything"));
        assertFalse(map.containsKey("anything"));
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertEquals("{}", map.toString());

        // Clear empty map
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void testStringKeys() {
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("", 4); // Empty string key

        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
        assertEquals(3, map.get("cherry"));
        assertEquals(4, map.get(""));

        assertTrue(map.containsKey(""));
        assertFalse(map.containsKey(" ")); // Space is different from empty
        assertEquals(4, map.size());
    }
}