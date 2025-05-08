package threading;


public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Create multiple threads
        Thread t1 = new DepositThread(account);
        Thread t2 = new WithdrawThread(account);
        Thread t3 = new WithdrawThread(account);

        // Set thread names for clarity
        t1.setName("Depositor");
        t2.setName("Withdrawer 1");
        t3.setName("Withdrawer 2");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}

