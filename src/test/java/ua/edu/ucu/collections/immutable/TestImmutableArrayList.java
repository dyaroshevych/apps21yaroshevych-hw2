package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.Queue;

import static org.junit.Assert.*;

public class TestImmutableArrayList {
    private ImmutableArrayList immutableArray;
    Object[] array;

    @Before
    public void setUp() {
        array = new Integer[] {-1, -20, 35, -5};
        immutableArray = new ImmutableArrayList(array);
    }

    @Test
    public void testAdd() {
        ImmutableList arr2 = immutableArray.add(1, 10);
        Integer[] expected = new Integer[] {-1, 10, -20, 35, -5};
        assertArrayEquals(array, immutableArray.toArray());
        assertArrayEquals(expected, arr2.toArray());
    }

    @Test
    public void testAddAll() {
        ImmutableList arr2 = immutableArray.addAll(1, new Integer[] {1, 2, 3});
        Integer[] expected = new Integer[] {-1, 1, 2, 3, -20, 35, -5};
        assertArrayEquals(array, immutableArray.toArray());
        assertArrayEquals(expected, arr2.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testErrorOnAdd() {
        immutableArray.get(9);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testErrorOnAddAll() {
        immutableArray.addAll(10, new Integer[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testErrorOnRemove() {
        immutableArray.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThrowErrorOnSet() {
        immutableArray.set(10, 10);
    }

    @Test
    public void testRemove() {
        ImmutableList arr2 = immutableArray.remove(1);
        Integer[] expected = new Integer[] {-1, 35, -5};
        assertArrayEquals(array, immutableArray.toArray());
        assertArrayEquals(expected, arr2.toArray());
    }

    @Test
    public void testSet() {
        ImmutableList arr2 = immutableArray.set(1, 1);
        Integer[] expected = new Integer[] {-1, 1, 35, -5};
        assertArrayEquals(array, immutableArray.toArray());
        assertArrayEquals(expected, arr2.toArray());
    }

    @Test
    public void testIndexOf() {
        assertEquals(2, immutableArray.indexOf(35));
    }

    @Test
    public void testSize() {
        assertEquals(4, immutableArray.size());
    }

    @Test
    public void testClear() {
        ImmutableList arr2 = immutableArray.clear();
        Integer[] expected = new Integer[0];
        assertArrayEquals(expected, arr2.toArray());
    }

    @Test
    public void testIsEmpty() {
        ImmutableList arr2 = immutableArray.clear();
        assertTrue(arr2.isEmpty());
        assertFalse(immutableArray.isEmpty());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(immutableArray.toArray(), new Integer[] {-1, -20, 35, -5});
    }
}