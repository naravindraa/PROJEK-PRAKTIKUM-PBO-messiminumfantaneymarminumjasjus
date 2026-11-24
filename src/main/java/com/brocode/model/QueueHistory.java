package com.brocode.model;
import java.time.LocalDateTime;
public record QueueHistory(String queueNumber, String oldStatus, String newStatus, LocalDateTime changedAt) {}
