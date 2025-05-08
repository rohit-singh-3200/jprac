package threading;


class DepositThread extends Thread {
    private BankAccount account;

    public DepositThread(BankAccount acc) {
        this.account = acc;
    }

    public void run() {
        account.deposit(500);
    }
}