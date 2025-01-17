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
                default:
                    System.out.println("Invalid choice");
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
        System.out.print("Enter PIN (4 digits): ");
        int pin = Integer.parseInt(sc.nextLine());
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
                userMenu();
                return;
            } else {
                System.out.println("Invalid Social Security Number or PIN. Try again.");
            }
        }
        System.out.println("Maximum attempts reached. Exiting...");
    }

    private static void userMenu() {
        boolean active = true;
        while (active) {
            System.out.println("\n1. View Accounts");
            System.out.println("2. Deposit Money");
            System.out.println("3. Make a Transfer");
            System.out.println("4. Log out");
            System.out.print("Enter your choice please: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    viewAccounts();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    makeTransfer();
                    break;
                case 4:
                    logOut();
                    active = false; // Exit user menu
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
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
        System.out.print("Choose account to transfer from (Salary/Savings): ");
        String fromAccountType = sc.nextLine();
        System.out.print("Enter recipient account number: ");
        long toAccountNumber = getLongInput("Invalid account number. Please enter a valid number: ");
        double amount = getDoubleInput("Enter transfer amount: ");
        Bank.transfer(currentUser, fromAccountType, toAccountNumber, amount);
    }

    private static void logOut() {
        System.out.println("Logging out...");
        currentUser = null;
    }

    private static int getIntInput(String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static double getDoubleInput(String errorMessage) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static long getLongInput(String errorMessage) {
        while (true) {
            try {
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}