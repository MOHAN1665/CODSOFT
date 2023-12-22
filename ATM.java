import java.util.Scanner;

// BankAccount class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
}

// ATM class to handle user transactions
public class ATM {
    private final BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(withdrawalAmount);
                }
                case 4 -> {
                    exit = true;
                    System.out.println("Thank you for using the ATM.");
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
    }

    public void checkBalance() {
        double balance = userAccount.getBalance();
        System.out.println("Your current balance is: " + balance);
    }

    public void deposit(double amount) {
        userAccount.deposit(amount);
        System.out.println("Deposit successful. Updated balance: " + userAccount.getBalance());
    }

    public void withdraw(double amount) {
        boolean success = userAccount.withdraw(amount);
        if (success) {
            System.out.println("Withdrawal successful. Updated balance: " + userAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(1000.0);

        // Create an ATM instance and link it to the bank account
        ATM atm = new ATM(account);

        // Run the ATM
        atm.run();
    }
}
