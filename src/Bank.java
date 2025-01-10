import java.util.ArrayList;
import java.util.List;

public class Bank {
    // List to store LexAccount objects
    public static List<User> users = new ArrayList<>();
    public static long accountNumber = 0;

    // Constructor for LexBank
    private Bank() {
    }

    // Method to create a new user with the given name
    public static void createUser(String socialSecurityNumber, int pin) {
        if (validationSocialSecurityNumber(socialSecurityNumber)) {
            User user = new User(socialSecurityNumber, pin);
            users.add(user);
            System.out.println("User created with Salary and Savings accounts.");
        }
    }

    // validation for person id 10 digits, according to the format ######-####
    public static boolean validationSocialSecurityNumber(String socialSecurityNumber) {

        if (!socialSecurityNumber.matches("^\\d{6}-\\d{4}$")) {
            System.out.println(" Social Security Number is invalid! It must be in the format ######-####.");
            return false;
        }
        return true;
    }

    public static User authenticate(String socialSecurityNumber, int pin) {
        for (User user : users) {
            if (user.getSocialSecurityNumber().equals(socialSecurityNumber) && user.getPin() == pin) {
                return user;
            }
        }
        return null;
    }

    // Method to transfer between accounts that takes account numbers and amount as arguments
    public static void transfer(User user, String fromAccountType, long toAccountNumber, double amount) {
        Account fromAccount = fromAccountType.equalsIgnoreCase("Salary") ? user.getSalaryAccount() : user.getSavingsAccount();
        Account toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Account not found.");
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println("Transfer successful.");
    }

    public static Account findAccountByNumber(long accountNumber) {
        for (User user : users) {
            if (user.getSalaryAccount().getAccountNumber() == accountNumber) {
                return user.getSalaryAccount();
            } else if (user.getSavingsAccount().getAccountNumber() == accountNumber) {
                return user.getSavingsAccount();
            }
        }
        return null;


        //
    }
}