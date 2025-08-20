# Custom Java Collections & Utility Classes

## Purpose

This project serves multiple purposes:
- Educational tool to understand the internal workings of common Java data structures
- Interview preparation by practicing fundamental data structure implementations
- Reference for clean, well-documented code examples
- Learning resource for exploring different approaches to common programming problems

## Features

### Implemented Data Structures

**ArrayList**
- Dynamic array implementation with automatic resizing
- Generic type support with efficient memory management
- Add, remove, insert, and search operations

**HashMap**
- Hash table implementation with collision handling
- Key-value pair storage with dynamic resizing
- Put, get, remove, and containsKey operations

**HashSet**
- Set implementation using hash table backing
- Unique element storage with fast lookup
- Add, remove, contains, and iteration support

**LinkedList**
- Doubly linked list implementation
- Efficient insertion and deletion at any position
- Forward and backward traversal capabilities

**Queue**
- First-In-First-Out (FIFO) queue implementation
- Enqueue, dequeue, peek, and size operations
- Circular buffer optimization for efficiency

**Stack**
- Last-In-First-Out (LIFO) stack implementation
- Push, pop, peek, and isEmpty operations
- Array-based backing with dynamic resizing

**StringBuilder**
- Dynamic string manipulation with automatic resizing
- Efficient append, insert, delete, and replace operations
- Memory-optimized buffer management with growth factor
- Comprehensive error handling for boundary conditions

### Testing Infrastructure
- Comprehensive test suites with both JUnit and manual test runners
- Edge case coverage including boundary conditions and error scenarios
- Performance testing with basic benchmarks
- Detailed test descriptions and expected behaviors



## Performance Characteristics

| Data Structure | Access | Search | Insertion | Deletion | Space |
|----------------|--------|--------|-----------|----------|--------|
| ArrayList      | O(1)   | O(n)   | O(n)      | O(n)     | O(n)   |
| HashMap        | O(1)   | O(1)   | O(1)      | O(1)     | O(n)   |
| HashSet        | N/A    | O(1)   | O(1)      | O(1)     | O(n)   |
| LinkedList     | O(n)   | O(n)   | O(1)      | O(1)     | O(n)   |
| Queue          | O(1)   | N/A    | O(1)      | O(1)     | O(n)   |
| Stack          | O(1)   | N/A    | O(1)      | O(1)     | O(n)   |
| StringBuilder  | O(1)   | N/A    | O(n)      | O(n)     | O(n)   |



