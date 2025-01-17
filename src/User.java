public class User {
    private int pin;
    private String socialSecurityNumber;
    private  Account salaryAccount;
    private  Account savingAccont;

    public User(String socialSecurityNumber, int pin) {
        this.pin = pin;
        this.socialSecurityNumber = socialSecurityNumber;
        this.salaryAccount = new Account("Salary", Bank.generateAccountNumber());
        this.savingAccont = new Account("Saving", Bank.generateAccountNumber());
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

}
