import java.util.Scanner;
import java.io.*;

interface BankAccountOperations {
    void deposit(double amount);

    void withdraw(double amount);

    double checkBalance();
}

class BankAccount implements BankAccountOperations {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    @Override
    public double checkBalance() {
        return balance;
    }
}

interface ATMOperations {
    void displayMenu();

    void processOption(int option);
}

class ATM implements ATMOperations {
    private BankAccountOperations bankAccount;

    public ATM(BankAccountOperations bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    @Override
    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                bankAccount.deposit(depositAmount);
                System.out.println("Deposit successful. New balance: " + bankAccount.checkBalance());
                break;

            case 2:
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = scanner.nextDouble();
                bankAccount.withdraw(withdrawAmount);
                System.out.println("Withdrawal successful. New balance: " + bankAccount.checkBalance());
                break;

            case 3:
                System.out.println("Current Balance: " + bankAccount.checkBalance());
                break;

            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }
}

class ATMMachine {
    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(0);

        ATM atm = new ATM(bankAccount);

        while (true) {
            atm.displayMenu();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            atm.processOption(option);
        }
    }
}
