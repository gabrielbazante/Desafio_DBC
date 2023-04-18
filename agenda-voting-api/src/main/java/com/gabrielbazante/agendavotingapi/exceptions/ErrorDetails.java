package com.gabrielbazante.agendavotingapi.exceptions;

import java.time.LocalDateTime;
import java.util.Date;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamp, int status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(Date date, String message2, String simpleName) {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
