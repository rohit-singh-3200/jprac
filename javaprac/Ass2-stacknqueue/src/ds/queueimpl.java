package ds;

import java.util.*;

public class queueimpl implements operations {
    Queue<Integer> queue = new LinkedList<>();

    public void push(int value) {
        queue.offer(value); // enqueue
    }

    public void pop() {
        if (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.poll());
        } else {
            System.out.println("Queue is empty");
        }
    }

    public void display() {
        System.out.println("Queue: " + queue);
    }
}
