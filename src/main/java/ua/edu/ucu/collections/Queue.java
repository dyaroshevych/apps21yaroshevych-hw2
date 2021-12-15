package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue(Object[] objects) {
        queue = new ImmutableLinkedList(objects);
    }

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getHead().getValue();
    }

    public Object dequeue() {
        Object head = queue.getHead().getValue();
        try {
            queue = queue.removeFirst();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return head;
    }

    public void enqueue(Object e) {
        this.queue = queue.addLast(e);
//        for (Object elem: queue.toArray()) {
//            System.out.println(elem);
//        }
    }
}
