package com.website.dao;

public class ApiResponse<T> {
    private String message;
    private int errorCode;
    private boolean status;
    private T data;

    // Constructors
    public ApiResponse(String message, int errorCode, boolean status, T data) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.data = data;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
