# FINSERV-4198: Build multi-channel notification dispatcher

**Status:** In Progress · **Priority:** High
**Sprint:** Sprint 27 · **Story Points:** 5
**Reporter:** Ravi Krishnan (Notifications Lead) · **Assignee:** You (Intern)
**Due:** End of sprint (Friday)
**Labels:** `backend`, `java`, `notifications`, `messaging`
**Task Type:** Feature Ship

---

## Description

The `TemplateEngine` renders notification templates. Build the `NotificationDispatcher` that sends notifications through multiple channels (email, SMS, push) with retry logic, priority queuing, and delivery tracking. Implement the TODOs in `NotificationDispatcher.java`.

## Acceptance Criteria

- [ ] `dispatch()` sends notification through the correct channel
- [ ] Failed sends are retried with exponential backoff (max 3 retries)
- [ ] High-priority notifications jump the queue
- [ ] Delivery status is tracked per notification
- [ ] All unit tests pass
