# Day 14 — Payment System

A menu-driven console program that takes payments through three
different methods — UPI, Card and Cash — all handled through one
common interface. UPI and Card payments can also be refunded;
Cash payments cannot. Every payment and refund is recorded in a
transaction history, and per-method totals are shown on demand.

## Concepts used

- **Interface** — `Payment` declares `pay(...)` and `getMethod()`;
  every payment class must implement both
- **Multiple interfaces** — `UpiPayment` and `CardPayment` implement
  both `Payment` and `Refundable`; `CashPayment` implements only
  `Payment`, so it has no refund behaviour
- **Polymorphism via interface reference** — `doPay(Payment payment)`
  works for UPI, Card and Cash without knowing the exact class;
  the right `pay(...)` runs at runtime
- **Classes and objects** — each payment method keeps its own
  `totalPaid` state in its own object
- **ArrayList** — every transaction is stored in one
  `ArrayList<Transaction>` history
- **switch expression** — the refund choice maps to a `Refundable`
  object with `switch (...) { case 1 -> upi; ... }`
- **Input validation** — non-numbers and non-positive amounts are
  rejected and asked again; a refund larger than the amount paid
  by that method is refused
- **printf / String.format** — amounts printed to 2 decimal places

## How to run

```bash
javac PaymentSystem.java
java PaymentSystem
```

## Sample run

Paid 500 via UPI, 1200 via Card and 300 in Cash, refunded 200
from UPI, then showed the history and the totals.

```
=== Payment System ===

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: Enter amount: Paid 500.00 via UPI (thakur@okhdfcbank).

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: Enter amount: Paid 1200.00 via Card (**** 4242).

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: Enter amount: Paid 300.00 in Cash.

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: Refund from (1 = UPI, 2 = Card): Enter refund amount: Refunded 200.00 to UPI (thakur@okhdfcbank).

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: --- Transaction history ---
1. PAYMENT | UPI   | 500.00
2. PAYMENT | Card  | 1200.00
3. PAYMENT | Cash  | 300.00
4. REFUND  | UPI   | 200.00

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: UPI total: 300.00
Card total: 1200.00
Cash total: 300.00
Grand total: 1800.00

1. Pay with UPI
2. Pay with Card
3. Pay with Cash
4. Refund (UPI or Card)
5. Show transaction history
6. Show totals
7. Exit
Choose an option: Goodbye!
```
