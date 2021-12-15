package ua.edu.ucu.collections.immutable;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int length;

    public ImmutableLinkedList(Object[] elements) {
        length = elements.length;
        if (length > 0) {
            Node curr = new Node(elements[0]);
            head = curr;
            for (int idx = 1; idx < elements.length; idx++) {
                Node next = new Node(elements[idx]);
                curr.setNext(next);
                next.setPrevious(curr);
                curr = next;
            }
            tail = curr;
        }
    }

    public ImmutableLinkedList() {
        length = 0;
    }

    @Override
    public ImmutableList add(Object e) {
        return add(length, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }
        if (head == null) {
            return new ImmutableLinkedList(c);
        }
        if (index == 0) {
            return new ImmutableLinkedList(c).addAll(toArray());
        }

        Object[] elements = new Object[length + c.length];
        Node curr = head;
        for (int idx = 0; idx < index; idx++) {
            elements[idx] = curr.getValue();
            curr = curr.getNext();
        }
        for (int idx = index; idx < index + c.length; idx++) {
            elements[idx] = c[idx - index];
        }
        for (int idx = index + c.length; idx < length + c.length; idx++) {
            elements[idx] = curr.getValue();
            curr = curr.getNext();
        }
        tail = curr;

        return new ImmutableLinkedList(elements);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = head;
        for (int idx = 0; idx < index; idx++) {
            curr = curr.getNext();
        }

        return curr.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }

        Object[] elements = new Object[length - 1];
        Node curr = head;
        if (index == 0) {
            head = head.getNext();
        }
        for (int idx = 0; idx < index; idx++) {
            elements[idx] = curr.getValue();
            curr = curr.getNext();
        }
        for (int idx = index + 1; idx < length; idx++) {
            curr = curr.getNext();
            elements[idx - 1] = curr.getValue();
        }

        return new ImmutableLinkedList(elements);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        Object[] elements = toArray();
        elements[index] = e;
        return new ImmutableLinkedList(elements);
    }

    @Override
    public int indexOf(Object e) {
        Node curr = head;
        for (int idx = 0; idx < length; idx++) {
            if (curr.getValue().equals(e)) {
                return idx;
            }
            curr = curr.getNext();
        }

        return -1;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Node curr = head;
        Object[] elements = new Object[length];
        int idx = 0;
        while (!(curr == null)) {
            elements[idx] = curr.getValue();
            idx++;
            curr = curr.getNext();
        }
        return elements;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(length, e);
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Object getFirst() {
        return head;
    }

    public Object getLast() {
        return tail;
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(length - 1);
    }
}
