# Beginner Explanatory Guide: FINSERV-4198: Build multi-channel notification dispatcher

> **Task Type**: Service Task  
> **Domain/Focus**: Backend Notifications System

---

## 1. The Goal (In-Depth Beginner Explanation)

### The Core Problem
The task at hand is to build a `NotificationDispatcher` that is responsible for sending notifications through various channels such as email, SMS, and push notifications. Currently, the system lacks a robust mechanism to handle these notifications effectively, which can lead to missed communications with users. This is particularly critical in applications where timely notifications are essential, such as alerts for transactions, reminders, or important updates.

The existing implementation does not support features like retry logic for failed deliveries, priority queuing for urgent notifications, or tracking the delivery status of each notification. Without these features, users may not receive important messages, and the system may not be able to recover from temporary failures in sending notifications. This can result in a poor user experience and a lack of trust in the application.

### Jargon Buster (Key Terms Explained)
* **Notification Channels**: These are the different methods through which notifications can be sent to users. For example, email is a channel where messages are sent to a user's email address, while SMS is a channel that sends text messages to a mobile phone. Push notifications are alerts sent directly to a user's device from an application.
  
* **Exponential Backoff**: This is a strategy used in network communications to handle retries after a failure. Instead of retrying immediately, the system waits for a progressively longer period before each retry attempt (e.g., 100ms, 200ms, 400ms). This helps to reduce the load on the server and increases the chances of success on subsequent attempts.

* **Priority Queuing**: This is a method of organizing tasks or messages based on their importance. In this context, high-priority notifications are processed before normal ones, ensuring that urgent messages reach users without delay.

* **Delivery Tracking**: This refers to the ability to monitor the status of sent notifications, including whether they were successfully delivered or failed. This information is crucial for debugging issues and ensuring that users receive important messages.

### Expected Outcome
After implementing the `NotificationDispatcher`, the system should be able to send notifications through the correct channels based on the recipient's information. For example, if the recipient's address contains an "@" symbol, the notification should be sent via email; if it starts with a "+", it should be sent via SMS; otherwise, it should be sent as a push notification. 

**Before vs. After**:
- **Before**: Notifications may fail without retries, and there is no tracking of delivery status.
- **After**: Notifications are sent through the appropriate channels, with retries on failure, priority handling, and delivery tracking implemented.

---

## 2. Related Coding Concepts & Syntax (50% Theory, 50% Practice)

### Concept 1: Queues
#### 📘 Theoretical Overview (50%)
* **Why it exists**: A queue is a data structure that follows the First-In-First-Out (FIFO) principle, meaning that the first element added to the queue will be the first one to be removed. Queues are essential for managing tasks that need to be processed in a specific order, such as notifications where high-priority messages should be handled before normal ones.
* **Key Mechanisms**: In Java, queues can be implemented using classes like `LinkedList` or `PriorityQueue`. The `Queue` interface provides methods like `add()`, `remove()`, and `peek()` to manage the elements in the queue.

#### 💻 Syntax & Practical Examples (50%)
* **Language Syntax**:
  ```java
  Queue<String> queue = new LinkedList<>();
  queue.add("First");
  queue.add("Second");
  String firstElement = queue.remove(); // Removes "First"
  ```

* **Real-World Application**:
  ```java
  Queue<Map<String, Object>> highPriorityQueue = new LinkedList<>();
  Map<String, Object> notification = new HashMap<>();
  notification.put("recipient", "user@example.com");
  notification.put("message", "Your transaction was successful!");
  highPriorityQueue.add(notification); // Add notification to the high priority queue
  ```

---

## 3. Step-by-Step Logic & Walkthrough

1. **Step 1: Locate and Analyze the Target File**
   * Navigate to the `NotificationDispatcher.java` file in the `s-w05-task-04` folder.
   * Focus on the `dispatch()` method, which is currently marked with a TODO comment. This is where the main logic for sending notifications will be implemented.

2. **Step 2: Input Verification & Validation**
   * Check the inputs to the `dispatch()` method: `recipient`, `templateName`, `variables`, and `priority`.
   * Ensure that `recipient` is not null or empty, `templateName` exists in the `TemplateEngine`, and `priority` is either "high", "normal", or "critical".

3. **Step 3: Core Implementation / Modification**
   * Render the notification message using the `templateEngine.render(templateName, variables)` method.
   * Determine the notification channel based on the recipient's address.
   * Add the notification to the appropriate queue based on its priority.
   * Implement the retry logic using a loop that attempts to send the notification up to three times with exponential backoff if it fails.

4. **Step 4: Output Verification & Testing**
   * After implementing the `dispatch()` method, run the unit tests to ensure that all functionalities work as expected.
   * Check the delivery log to verify that notifications are logged correctly with their status.

---

## 4. Detailed Walkthrough of Test Cases

### Test Case 1: Standard / Success Case
* **Description**: This test checks if a notification is successfully sent to a valid email recipient.
* **Inputs**:
  ```json
  {
    "recipient": "user@example.com",
    "templateName": "transaction_success",
    "variables": {"amount": "$100"},
    "priority": "normal"
  }
  ```
* **Step-by-Step Execution Trace**:
  1. Input values are received by the `dispatch()` function.
  2. The function verifies that the recipient is valid and the template exists.
  3. The message is rendered as "Your transaction of $100 was successful!".
  4. The function determines the channel as "email" and attempts to send the notification.
  5. The notification is sent successfully on the first attempt.
  6. The delivery result is logged as a success.

* **Expected Output**: 
  ```json
  {
    "status": "success",
    "channel": "email",
    "attempts": 1,
    "timestamp": "2023-10-01T12:00:00Z"
  }
  ```

### Test Case 2: Edge Case / Validation Fail
* **Description**: This test checks how the system handles an invalid recipient.
* **Inputs**:
  ```json
  {
    "recipient": "",
    "templateName": "transaction_success",
    "variables": {"amount": "$100"},
    "priority": "normal"
  }
  ```
* **Step-by-Step Execution Trace**:
  1. Input values are received by the `dispatch()` function.
  2. The function checks the recipient and finds it is empty.
  3. The validation block detects that the input is invalid and does not proceed further.
  4. The function throws an `IllegalArgumentException` indicating that the recipient is invalid.

* **Expected Output**: 
  ```json
  {
    "error": "Invalid recipient: recipient cannot be empty."
  }
  ``` 

This guide provides a comprehensive understanding of the task at hand, the concepts involved, and the steps necessary to implement the solution effectively.