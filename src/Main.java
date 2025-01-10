import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private List<User> users = new ArrayList<User>();
    private static User currentUser = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean active = true;
        while (active) {
            displayMenu();
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    active = false;
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Welcome To Bank Application");
        System.out.println("1. Create user");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        System.out.print("Enter your choice please: ");
    }

    private static void createUser() {
        System.out.print("Enter Social Security Number (######-####): ");
        String socialSecurityNumber = sc.nextLine();
        System.out.print("Your Social Security Number is : " + socialSecurityNumber);
        System.out.print("Enter PIN (4 digits): ");
        int pin = Integer.parseInt(sc.nextLine());
        System.out.print("Your Pin Number is : " + pin);
        Bank.createUser(socialSecurityNumber, pin);
    }


    private static void logIn() {
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter Social Security Number: ");
            String socialSecurityNumber = sc.nextLine();
            System.out.print("Enter PIN: ");
            int pin = Integer.parseInt(sc.nextLine());

            currentUser = Bank.authenticate(socialSecurityNumber, pin);
            if (currentUser != null) {
                System.out.println("Logged in successfully.");

                // User menu
                boolean active = true;
                while (active) {
                    System.out.println("\n1. View Accounts");
                    System.out.println("2. Make a Transfer");
                    System.out.println("3. Log out");
                    System.out.print("Enter your choice please: ");
                    int choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            viewAccounts();
                            break;
                        case 2:
                            depositMoney();
                        case 3:
                            makeTransfer();
                            break;
                        case 4:
                            active = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
                return;
            } else {
                System.out.println("Invalid Social Security Number or PIN. Try again.");
            }
        }
        System.out.println("Maximum attempts reached. Exiting...");
    }

    private static void viewAccounts() {
        System.out.println("Salary Account: " + currentUser.getSalaryAccount().getAccountNumber() + " Balance: " + currentUser.getSalaryAccount().getBalance());
        System.out.println("Savings Account: " + currentUser.getSavingsAccount().getAccountNumber() + " Balance: " + currentUser.getSavingsAccount().getBalance());
    }

    private static void depositMoney() {
        System.out.print("Choose account to deposit (Salary/Savings): ");
        String accountType = sc.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine());
        if (accountType.equalsIgnoreCase("Salary")) {
            currentUser.getSalaryAccount().deposit(amount);
        } else if (accountType.equalsIgnoreCase("Savings")) {
            currentUser.getSavingsAccount().deposit(amount);
        } else {
            System.out.println("Invalid account type.");
        }
    }

    private static void makeTransfer() {
        System.out.print("Choose from which account (Salary/Savings): ");
        String fromAccountType = sc.nextLine();
        System.out.print("Enter recipient account number: ");
        long toAccountNumber = Long.parseLong(sc.nextLine());
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        Bank.transfer(currentUser, fromAccountType, toAccountNumber, amount);
    }
}