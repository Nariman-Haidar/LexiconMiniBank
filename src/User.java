public class User {
    private int pin;
    private String socialSecurityNumber;
    private static Account salaryAccount;
    private static Account savingAccont;

    public User(String socialSecurityNumber, int pin) {
        this.pin = pin;
        this.socialSecurityNumber = socialSecurityNumber;
        this.salaryAccount = new Account("Salary");
        this.savingAccont = new Account("Saving");
    }

    // Getter
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public int getPin() {
        return pin;
    }

    public Account getSavingsAccount() {
        return savingAccont;
    }
    public Account getSalaryAccount() {
        return salaryAccount;
    }
    /*public static long createUser(String name) {
            Account account = new Account(name);
            accounts.add(salaryAccount);
            accounts.add(savingAccont);
            System.out.println("Account created for " + name);
        System.out.println("You have now two accounts created for you " + name + salaryAccount + savingAccont);
        return account.getAccountNumber();
        }

    // validation for person id 10 digits, according to the format ######-####
    public static boolean validationSocialSecurityNumber ( String socialSecurityNumber ) {

        if (!socialSecurityNumber.matches("^\\d{6}-\\d{4}$")) {
            System.out.println(" Social Security Number is invalid! It must be in the format ######-####.");
            return false;
        }
        return true;
    }
    public boolean validationOfPin(int pin) {
        return this.pin == pin;
        // return this.pin.equals(enterYourPin);
    }


     */

}
