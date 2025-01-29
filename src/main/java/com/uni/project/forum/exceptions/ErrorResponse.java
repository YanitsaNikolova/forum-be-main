package com.uni.project.forum.exceptions;

public class ErrorResponse {
    private int status;      // HTTP статус код
    private String message;  // Съобщение за грешка

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Гетъри и сетъри
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}