package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.Node;

public class Stack {
    private ImmutableLinkedList stack;

    public Stack(Object[] objects) {
        Object[] reversed = new Object[objects.length];
        for (int idx = 0; idx < objects.length; idx++) {
            reversed[idx] = objects[objects.length - idx - 1];
        }
        stack = new ImmutableLinkedList(reversed);
    }

    public Stack() {
        stack = new ImmutableLinkedList();
    }

    public Object peek() {
        Node head = stack.getHead();
        return head.equals(null) ? head : head.getValue();
    }

    public Object pop() {
        Object head = peek();

        try {
            stack = stack.removeFirst();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return head;
    }

    public void push(Object e) {
        stack = stack.addFirst(e);
    }

    public int size() {return stack.size();}
}