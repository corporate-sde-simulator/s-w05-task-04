/**
 * Template Engine — renders notification templates with variables.
 *
 * This module is COMPLETE. Your task is in NotificationDispatcher.java.
 *
 * Author: Ravi Krishnan (Notifications team)
 * Last Modified: 2026-03-05
 */

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    private Map<String, String> templates = new HashMap<>();
    private Pattern placeholder = Pattern.compile("\\{\\{(\\w+)\\}\\}");

    public void registerTemplate(String name, String template) {
        templates.put(name, template);
    }

    public String render(String templateName, Map<String, String> variables) {
        String template = templates.get(templateName);
        if (template == null) {
            throw new IllegalArgumentException("Template not found: " + templateName);
        }

        StringBuffer result = new StringBuffer();
        Matcher matcher = placeholder.matcher(template);
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = variables.getOrDefault(key, "{{" + key + "}}");
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public boolean hasTemplate(String name) {
        return templates.containsKey(name);
    }

    public int getTemplateCount() {
        return templates.size();
    }
}
