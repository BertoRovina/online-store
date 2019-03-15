package com.hrovina.onlinestore.controllers.exceptions;

import java.io.Serializable;

public class FieldMessages implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldMessages() {
    }

    public FieldMessages(String fieldMessage, String message) {
        this.fieldName = fieldMessage;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
