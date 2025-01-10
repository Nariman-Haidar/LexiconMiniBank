public class Account {
    private String accountType;
    private double balance;
    private long accountNumber = 0;

    public Account(String accountType) {
        this.accountType = accountType;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Getter method for name
    public String getAccountType() {
        return accountType;
    }

    // Getter method for balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit an amount to the account
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to " + accountType);
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from " + accountType);
        } else {
            System.out.println("Insufficient funds in " + accountType);
        }
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
