package com.hrovina.onlinestore.controllers.exceptions;

import java.io.Serializable;

public class FieldMessages implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldMessage;
    private String message;

    public FieldMessages() {
    }

    public FieldMessages(String fieldMessage, String message) {
        this.fieldMessage = fieldMessage;
        this.message = message;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
