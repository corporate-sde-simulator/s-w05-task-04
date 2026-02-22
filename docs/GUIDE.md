# Learning Guide - Java

> **Welcome to Service-Track Week 5, Task 4!**
> This is a **learning task**. This guide teaches you the Java concepts you need and tells you exactly where to look and what to fix. Take your time and read carefully.

---

## What You Need To Do (Summary)

1. **Read** `TICKET.md` to understand the task
2. **Read** this guide to learn the Java syntax you'll need
3. **Find the bugs** in the `src/` files (look for `// BUG:` or `# BUG:` comments)
4. **Fix the bugs** using what you learned
5. **Run the tests** to verify your fix: `javac src/*.java && java -cp .:junit-5.jar org.junit.runner.JUnitCore`
6. **Add new tests** in the `tests/` folder to prove your fix works

---

## Java Quick Reference

### Variables and Types
```java
String name = "Alice";              // String (capital S)
int count = 42;                     // integer
double price = 19.99;               // decimal
boolean isActive = true;            // boolean
List<String> items = new ArrayList<>();  // list
Map<String, Object> config = new HashMap<>();  // dictionary/map
```

### Methods (Functions)
```java
public String greet(String name) {
    return "Hello, " + name + "!";
}

// With default-like behavior:
public String greet(String name, String greeting) {
    return greeting + ", " + name + "!";
}
```

### Classes
```java
public class Calculator {
    private List<Integer> history = new ArrayList<>();  // private field

    public Calculator() {           // Constructor
        // initialization
    }

    public int add(int a, int b) {  // Public method
        int result = a + b;
        history.add(result);
        return result;
    }

    public List<Integer> getHistory() {
        return new ArrayList<>(history);  // Return a copy
    }
}

// Using it:
Calculator calc = new Calculator();
calc.add(2, 3);
System.out.println(calc.getHistory());  // [5]
```

### Maps (Key-Value Storage)
```java
Map<String, Object> user = new HashMap<>();
user.put("name", "Alice");              // Add
user.get("name");                       // Access: "Alice"
user.getOrDefault("email", "N/A");      // Safe access
user.containsKey("name");              // Check: true
user.size();                            // Count: 1
```

### Lists
```java
List<String> items = new ArrayList<>();
items.add("apple");                     // Add
items.get(0);                           // Access by index
items.size();                           // Length
items.remove(0);                        // Remove by index
for (String item : items) {            // Loop
    System.out.println(item);
}
```

### Error Handling
```java
try {
    Object result = riskyOperation();
} catch (IllegalArgumentException e) {  // Specific exception
    System.err.println("Bad input: " + e.getMessage());
} catch (Exception e) {                 // Any exception
    System.err.println("Error: " + e.getMessage());
}
```

### Common Patterns You'll See
```java
// Null checks
if (value == null) { return "No value"; }
if (value != null && !value.isEmpty()) { /* safe to use */ }

// Comparison (IMPORTANT!)
// Use .equals() for strings and objects, NOT ==
"hello".equals(other)     // correct
"hello" == other          // WRONG (compares references, not content)

// Imports
import java.util.*;
import java.util.Map;
```

### How to Run Tests
```bash
# If using JUnit with Maven:
mvn test

# If running directly:
java -cp .:junit-5.jar org.junit.runner.JUnitCore TestClass
```

### How to Add a Test
```java
@Test
void shouldDoSomethingSpecific() {
    MyClass obj = new MyClass();
    Object result = obj.method(inputData);
    assertNotNull(result);                    // Not null
    assertEquals(expected, actual);           // Exact match
    assertTrue(result.size() > 0);           // Condition check
}
```

---

## Project Structure

| File | Purpose |
|------|---------|
| `TICKET.md` | Your task assignment - **read this first!** |
| `src/NotificationDispatcher.java` | Main source code - **has bugs to fix** |
| `src/TemplateEngine.java` | Supporting code - **may also have bugs** |
| `tests/deliveryTracker.test.js` | Test file - **add your tests here** |
| `docs/DESIGN.md` | Architecture decisions (background reading) |
| `docs/GUIDE.md` | This learning guide |
| `.context/` | Meeting notes and PR comments (background context) |

---

## Where to Look and What to Fix


---

## Step-by-Step Workflow

1. Open a terminal and navigate to this task folder
2. Read `TICKET.md` completely
3. Open `src/` files and find the `BUG:` comments
4. Fix each bug (the comment tells you what's wrong)
5. Run the tests:
   ```bash
   javac src/*.java && java -cp .:junit-5.jar org.junit.runner.JUnitCore
   ```
6. If tests pass, you're done with the fix
7. **Bonus:** Add a new test to `tests/` that specifically tests the bug you fixed

---

## Common Mistakes to Avoid

- Don't change the function signatures (method names, parameters) - only fix the logic inside
- Don't delete the `BUG:` comments - they help reviewers understand what you changed
- Make sure all existing tests still pass after your changes
- If you're stuck, re-read the `TICKET.md` requirements section carefully
