// Day 6: Bank Account
// Concepts: classes, objects, constructors, instance methods,
// fields, the 'this' keyword, and basic validation.

import java.util.Scanner;

class BankAccount {
    // Fields (instance variables) - the data each account holds
    private String accountHolder;
    private long accountNumber;
    private double balance;

    // Constructor - runs once when a new account is created
    public BankAccount(String accountHolder, long accountNumber, double openingBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    // Deposit money into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be more than 0.");
            return;
        }
        balance += amount;
        System.out.println("Deposited: Rs " + amount);
    }

    // Withdraw money, only if enough balance is available
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be more than 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Not enough balance! Available: Rs " + balance);
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: Rs " + amount);
    }

    // Show the current balance
    public double getBalance() {
        return balance;
    }

    // Print all account details in one place
    public void showDetails() {
        System.out.println("----- Account Details -----");
        System.out.println("Holder Name   : " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance       : Rs " + balance);
    }
}

public class BankAccountApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter account number: ");
        long number = sc.nextLong();

        System.out.print("Enter opening balance: ");
        double opening = sc.nextDouble();

        // Create one BankAccount object using the constructor
        BankAccount account = new BankAccount(name, number, opening);

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Show Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    System.out.println("Current balance: Rs " + account.getBalance());
                    break;
                case 4:
                    account.showDetails();
                    break;
                case 5:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
