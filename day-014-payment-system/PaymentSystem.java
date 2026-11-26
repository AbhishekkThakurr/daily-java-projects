import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 14 — Payment System
 *
 * A menu-driven console program that takes payments through three
 * different methods — UPI, Card and Cash — all handled through one
 * common interface. UPI and Card payments can also be refunded;
 * Cash payments cannot.
 */
interface Payment {
    boolean pay(double amount);
    String getMethod();
}

interface Refundable {
    boolean refund(double amount);
}

class UpiPayment implements Payment, Refundable {
    private final String upiId;
    private double totalPaid;

    UpiPayment(String upiId) {
        this.upiId = upiId;
        this.totalPaid = 0;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            return false;
        }
        totalPaid += amount;
        System.out.printf("Paid %.2f via UPI (%s).%n", amount, upiId);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        if (amount <= 0 || amount > totalPaid) {
            return false;
        }
        totalPaid -= amount;
        System.out.printf("Refunded %.2f to UPI (%s).%n", amount, upiId);
        return true;
    }

    @Override
    public String getMethod() {
        return "UPI";
    }

    double getTotalPaid() {
        return totalPaid;
    }
}

class CardPayment implements Payment, Refundable {
    private final String lastFourDigits;
    private double totalPaid;

    CardPayment(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
        this.totalPaid = 0;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            return false;
        }
        totalPaid += amount;
        System.out.printf("Paid %.2f via Card (**** %s).%n", amount, lastFourDigits);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        if (amount <= 0 || amount > totalPaid) {
            return false;
        }
        totalPaid -= amount;
        System.out.printf("Refunded %.2f to Card (**** %s).%n", amount, lastFourDigits);
        return true;
    }

    @Override
    public String getMethod() {
        return "Card";
    }

    double getTotalPaid() {
        return totalPaid;
    }
}

class CashPayment implements Payment {
    private double totalPaid;

    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            return false;
        }
        totalPaid += amount;
        System.out.printf("Paid %.2f in Cash.%n", amount);
        return true;
    }

    @Override
    public String getMethod() {
        return "Cash";
    }

    double getTotalPaid() {
        return totalPaid;
    }
}

class Transaction {
    private final String method;
    private final double amount;
    private final boolean refund;

    Transaction(String method, double amount, boolean refund) {
        this.method = method;
        this.amount = amount;
        this.refund = refund;
    }

    @Override
    public String toString() {
        String type = refund ? "REFUND " : "PAYMENT";
        return String.format("%s | %-5s | %.2f", type, method, amount);
    }
}

public class PaymentSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Transaction> history = new ArrayList<>();

    public static void main(String[] args) {
        UpiPayment upi = new UpiPayment("thakur@okhdfcbank");
        CardPayment card = new CardPayment("4242");
        CashPayment cash = new CashPayment();

        System.out.println("=== Payment System ===");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Pay with UPI");
            System.out.println("2. Pay with Card");
            System.out.println("3. Pay with Cash");
            System.out.println("4. Refund (UPI or Card)");
            System.out.println("5. Show transaction history");
            System.out.println("6. Show totals");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> doPay(upi);
                case 2 -> doPay(card);
                case 3 -> doPay(cash);
                case 4 -> doRefund(upi, card);
                case 5 -> showHistory();
                case 6 -> showTotals(upi, card, cash);
                case 7 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please choose 1-7.");
            }
        }
        scanner.close();
    }

    private static void doPay(Payment payment) {
        System.out.print("Enter amount: ");
        double amount = readDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }
        if (payment.pay(amount)) {
            history.add(new Transaction(payment.getMethod(), amount, false));
        } else {
            System.out.println("Payment failed.");
        }
    }

    private static void doRefund(UpiPayment upi, CardPayment card) {
        System.out.print("Refund from (1 = UPI, 2 = Card): ");
        int choice = readInt();
        Refundable refundable = switch (choice) {
            case 1 -> upi;
            case 2 -> card;
            default -> null;
        };
        if (refundable == null) {
            System.out.println("Invalid choice. Cash payments cannot be refunded here.");
            return;
        }
        System.out.print("Enter refund amount: ");
        double amount = readDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }
        if (refundable.refund(amount)) {
            String method = (refundable == upi) ? "UPI" : "Card";
            history.add(new Transaction(method, amount, true));
        } else {
            System.out.println("Refund failed. Amount is more than the total paid by this method.");
        }
    }

    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("--- Transaction history ---");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }

    private static void showTotals(UpiPayment upi, CardPayment card, CashPayment cash) {
        double upiTotal = upi.getTotalPaid();
        double cardTotal = card.getTotalPaid();
        double cashTotal = cash.getTotalPaid();
        System.out.printf("UPI total: %.2f%n", upiTotal);
        System.out.printf("Card total: %.2f%n", cardTotal);
        System.out.printf("Cash total: %.2f%n", cashTotal);
        System.out.printf("Grand total: %.2f%n", upiTotal + cardTotal + cashTotal);
    }

    private static int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a whole number: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid amount: ");
            }
        }
    }
}
