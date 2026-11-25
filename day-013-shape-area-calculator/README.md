# Day 13 — Shape Area Calculator

A menu-driven console program that stores shapes and calculates their
area and perimeter. You can add circles, rectangles and triangles,
list them all, and see the combined totals.

Every shape is handled through one common type: an abstract `Shape`
class. `Circle`, `Rectangle` and `Triangle` each extend `Shape` and
provide their own `area()` and `perimeter()` formulas. Triangle area
uses Heron's formula from its three sides, and sides that cannot form
a real triangle are rejected.

## Concepts used

- **Abstract class** — `Shape` declares abstract `area()`, `perimeter()`
  and `details()` methods that every subclass must implement
- **Inheritance** — `Circle`, `Rectangle` and `Triangle` extend `Shape`
  and reuse its `name` field and `getName()` method via `super(...)`
- **Method overriding** — each subclass gives its own `@Override`
  version of `area()` and `perimeter()`
- **Polymorphism** — all shapes live in one `ArrayList<Shape>`; calling
  `shape.area()` runs the right formula for the actual object at runtime
- **ArrayList** — shapes are added, listed and totalled from one list
- **Input validation** — non-numbers and non-positive values are
  rejected and asked again; the triangle inequality (`a + b > c` etc.)
  is checked before a triangle is added
- **Math methods** — `Math.PI` for circles, `Math.sqrt(...)` in Heron's formula
- **printf / String.format** — areas and perimeters printed to 2 decimal places

## How to run

```bash
javac ShapeAreaCalculator.java
java ShapeAreaCalculator
```

## Sample run

Added: a circle of radius 5, a rectangle of 4 × 6, and a triangle
with sides 3, 4, 5 — then listed all shapes and the totals.

```
=== Shape Area Calculator ===

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: Enter radius: Circle added.

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: Enter length: Enter width: Rectangle added.

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: Enter side A: Enter side B: Enter side C: Triangle added.

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: --- All shapes ---
1. Circle (radius = 5.00) -> area = 78.54, perimeter = 31.42
2. Rectangle (length = 4.00, width = 6.00) -> area = 24.00, perimeter = 20.00
3. Triangle (sides = 3.00, 4.00, 5.00) -> area = 6.00, perimeter = 12.00

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: Shapes: 3, total area = 108.54, total perimeter = 63.42

1. Add Circle
2. Add Rectangle
3. Add Triangle
4. Show all shapes
5. Show total area and perimeter
6. Exit
Choose an option: Goodbye!
```
