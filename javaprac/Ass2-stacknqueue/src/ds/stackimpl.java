package ds;

import java.util.*;

public class stackimpl implements operations {
    Stack<Integer> stack = new Stack<>();

    public void push(int value) {
        stack.push(value);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        } else {
            System.out.println("Stack is empty");
        }
    }

    public void display() {
        System.out.println("Stack: " + stack);
    }
}
