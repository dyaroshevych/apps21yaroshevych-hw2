package ua.edu.ucu.collections.immutable;


public final class ImmutableArrayList implements ImmutableList {
    private Object[] array;

    public ImmutableArrayList(Object[] elements) {
        array = new Object[elements.length];
        int idx = 0;
        for (Object elem: elements) {
            array[idx] = elem;
            idx++;
        }
    }

    public ImmutableArrayList() {
        array = new Object[0];
    }

    @Override
    public ImmutableList add(Object e) {
        return add(array.length, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(array.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] updatedArray = new Object[array.length + c.length];

        for (int idx = 0; idx < index; idx++) {
            updatedArray[idx] = array[idx];
        }
        for (int idx = index; idx < index + c.length; idx++) {
            updatedArray[idx] = c[idx - index];
        }
        for (int idx = index + c.length; idx < array.length + c.length; idx++) {
            updatedArray[idx] = array[idx - c.length];
        }

        return new ImmutableArrayList(updatedArray);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] updatedArray = new Object[array.length - 1];

        for (int idx = 0; idx < index; idx++) {
            updatedArray[idx] = array[idx];
        }
        for (int idx = index + 1; idx < array.length; idx++) {
            updatedArray[idx - index] = array[idx];
        }

        return new ImmutableArrayList(updatedArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] updatedArray = new Object[array.length];

        for (int idx = 0; idx < index; idx++) {
            updatedArray[idx] = array[idx];
        }
        updatedArray[index] = e;
        for (int idx = index + 1; idx < array.length; idx++) {
            updatedArray[idx] = array[idx];
        }

        return new ImmutableArrayList(updatedArray);
    }

    @Override
    public int indexOf(Object e) {
        int counter = 0;

        for (Object elem: array) {
            if (elem.equals(e)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] updatedArray = new Object[array.length];

        for (int idx = 0; idx < array.length; idx++) {
            updatedArray[idx] = array[idx];
        }

        return updatedArray;
    }
}
