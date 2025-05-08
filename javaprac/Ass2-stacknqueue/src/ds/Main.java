package ds;

public class Main {
    public static void main(String[] args) {
        operations stack = new stackimpl();
        operations queue = new queueimpl();

        System.out.println("=== Stack Operations ===");
        stack.push(10);
        stack.push(20);
        stack.display();
        stack.pop();
        stack.display();

        System.out.println("\n=== Queue Operations ===");
        queue.push(1);
        queue.push(2);
        queue.display();
        queue.pop();
        queue.display();
    }
}
