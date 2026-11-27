# Day 15 — Movie Ticket Booking

A menu-driven console program that books seats in a small cinema
hall (5 rows x 8 seats). The ticket price depends on the row:
rows 1–2 are Regular, rows 3–4 are Premium and row 5 is Recliner.
Trying to book a seat that is already taken throws a custom
exception, which the program catches and reports instead of
crashing. Bookings can be cancelled, and the seat map, booking
list and total revenue are shown on demand.

## Concepts used

- **Custom exception** — `SeatNotAvailableException extends Exception`;
  `bookSeat(...)` declares `throws SeatNotAvailableException` and
  uses `throw` when the seat is already booked
- **try-catch** — `handleBooking()` catches the custom exception and
  prints its message, so one bad booking never stops the program
- **enum with fields and methods** — `SeatType` stores the price of
  each seat kind (`REGULAR(200)`, `PREMIUM(350)`, `RECLINER(500)`)
  and `SeatType.forRow(row)` picks the type from the row number
- **2D array** — `boolean[5][8]` tracks which seats are booked and
  is printed as a seat map (`.` = free, `X` = booked)
- **Classes and objects** — each confirmed ticket is a `Booking`
  object holding the customer name, row, seat and seat type
- **ArrayList** — all bookings are kept in one `ArrayList<Booking>`;
  cancellation removes the matching entry with `removeIf(...)`
- **Input validation** — non-numbers, empty names and out-of-range
  rows/seats are rejected and asked again

## How to run

```bash
javac MovieTicketBooking.java
java MovieTicketBooking
```

## Sample run

Booked a Premium seat (Row 3, Seat 4) and a Recliner seat
(Row 5, Seat 1), tried to book Row 3, Seat 4 again — the custom
exception rejected it — then showed the seat map, the bookings and
the revenue, cancelled the Recliner booking, and showed the
revenue again.

```
=== Movie Ticket Booking ===
Hall: 5 rows x 8 seats
Rows 1-2: Regular (200.00), Rows 3-4: Premium (350.00), Row 5: Recliner (500.00)

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Enter customer name: Enter row (1-5): Enter seat number (1-8): Booked: Thakur | Row 3, Seat 4 | PREMIUM | 350.00

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Enter customer name: Enter row (1-5): Enter seat number (1-8): Booked: Aarav | Row 5, Seat 1 | RECLINER | 500.00

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Enter customer name: Enter row (1-5): Enter seat number (1-8): Booking failed: Row 3, Seat 4 is already booked.

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: --- Seat map (. = free, X = booked) ---
     1 2 3 4 5 6 7 8 
R1   . . . . . . . .  (REGULAR)
R2   . . . . . . . .  (REGULAR)
R3   . . . X . . . .  (PREMIUM)
R4   . . . . . . . .  (PREMIUM)
R5   X . . . . . . .  (RECLINER)

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: --- All bookings ---
1. Thakur | Row 3, Seat 4 | PREMIUM | 350.00
2. Aarav | Row 5, Seat 1 | RECLINER | 500.00

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Total revenue: 850.00

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Enter row (1-5): Enter seat number (1-8): Cancelled booking for Row 5, Seat 1.

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Total revenue: 350.00

1. Book a seat
2. Cancel a booking
3. Show seat map
4. Show all bookings
5. Show total revenue
6. Exit
Choose an option: Goodbye!
```
