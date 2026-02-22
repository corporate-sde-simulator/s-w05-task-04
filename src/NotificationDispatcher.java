/**
 * Notification Dispatcher — sends notifications through multiple channels.
 *
 * YOU MUST IMPLEMENT the methods marked with TODO.
 * TemplateEngine is working — use it to render message templates.
 */

import java.util.*;

public class NotificationDispatcher {
    private TemplateEngine templateEngine;
    private List<Map<String, Object>> deliveryLog;
    private Queue<Map<String, Object>> highPriorityQueue;
    private Queue<Map<String, Object>> normalQueue;
    private int maxRetries;

    public NotificationDispatcher(TemplateEngine engine) {
        this.templateEngine = engine;
        this.deliveryLog = new ArrayList<>();
        this.highPriorityQueue = new LinkedList<>();
        this.normalQueue = new LinkedList<>();
        this.maxRetries = 3;
    }

    /**
     * Dispatch a notification to a recipient.
     *
     * TODO: Implement this method.
     * 1. Render the template using templateEngine.render(templateName, variables)
     * 2. Determine channel from recipient (email if contains @, SMS if starts with +, else push)
     * 3. If priority is "high" or "critical", add to highPriorityQueue, else normalQueue
     * 4. Attempt to send using sendToChannel(channel, recipient, renderedMessage)
     * 5. If send fails, retry with exponential backoff (wait 2^attempt * 100ms, max 3 retries)
     * 6. Log delivery result (success/failure, channel, attempts, timestamp)
     * 7. Return delivery result map
     */
    public Map<String, Object> dispatch(String recipient, String templateName,
                                         Map<String, String> variables, String priority) {
        // TODO: Implement dispatch logic
        return new HashMap<>();
    }

    /**
     * Process the queues (high priority first, then normal).
     *
     * TODO: Implement this method.
     * 1. Process all items in highPriorityQueue first
     * 2. Then process all items in normalQueue
     * 3. Return total number of notifications processed
     */
    public int processQueues() {
        // TODO: Process priority queues
        return 0;
    }

    /**
     * Send a message to a specific channel.
     * Simulated — returns true for success, false for failure.
     */
    private boolean sendToChannel(String channel, String recipient, String message) {
        // Simulate - 85% success rate
        return Math.random() > 0.15;
    }

    /**
     * Get delivery log.
     */
    public List<Map<String, Object>> getDeliveryLog() {
        return new ArrayList<>(deliveryLog);
    }

    /**
     * Get delivery stats.
     *
     * TODO: Implement this method.
     * Return map with: totalSent, totalFailed, byChannel (map of channel->count)
     */
    public Map<String, Object> getDeliveryStats() {
        // TODO: Compute delivery statistics
        return new HashMap<>();
    }
}
