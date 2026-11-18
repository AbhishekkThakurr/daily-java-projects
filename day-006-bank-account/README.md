# Day 6 — Bank Account

A small menu-driven bank account program. It creates one bank account and lets
you deposit money, withdraw money, check the balance, and view the account
details.

## What it covers

- **Classes and objects** — `BankAccount` is a class (the blueprint); `account`
  in `main` is an object created from it with `new`.
- **Constructor** — runs once when the object is created and sets the holder
  name, account number, and opening balance.
- **Fields** — `accountHolder`, `accountNumber`, and `balance` store each
  account's data.
- **`this` keyword** — `this.balance` means "this object's balance", so the
  constructor parameter and the field can share a name without confusion.
- **Instance methods** — `deposit()`, `withdraw()`, `getBalance()`, and
  `showDetails()` are actions an account object can perform.
- **Validation** — deposit and withdrawal reject zero/negative amounts, and
  withdrawal refuses when the balance is not enough.
- **do-while loop + switch** — the menu keeps running until you choose Exit.

## How to run

```bash
javac BankAccountApp.java
java BankAccountApp
```

## Sample run

```text
Enter account holder name: Thakur
Enter account number: 123456
Enter opening balance: 1000

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 1
Enter amount to deposit: 500
Deposited: Rs 500.0

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 2
Enter amount to withdraw: 200
Withdrawn: Rs 200.0

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 2
Enter amount to withdraw: 5000
Not enough balance! Available: Rs 1300.0

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 3
Current balance: Rs 1300.0

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 4
----- Account Details -----
Holder Name   : Thakur
Account Number: 123456
Balance       : Rs 1300.0

--- Bank Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. Show Account Details
5. Exit
Enter your choice: 5
Thank you for banking with us!
```
