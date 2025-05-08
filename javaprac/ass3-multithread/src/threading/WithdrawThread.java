package threading;


//WithdrawThread class
class WithdrawThread extends Thread {
 private BankAccount account;

 public WithdrawThread(BankAccount acc) {
     this.account = acc;
 }

 public void run() {
     account.withdraw(700);
 }
}