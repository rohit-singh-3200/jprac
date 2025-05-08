package threading;

class BankAccount {
    private int balance = 1000;

    // Synchronized deposit method
    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " depositing " + amount);
        balance += amount;
        System.out.println("Balance after deposit: " + balance);
    }

    // Synchronized withdraw method
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient balance. Current balance: " + balance);
        }
    }
}
