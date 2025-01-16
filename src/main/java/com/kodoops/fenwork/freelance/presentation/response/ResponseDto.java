package com.kodoops.fenwork.freelance.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class ResponseDto {
    private Object data;
    private String message;
    private String timestamp;
    private int status;

    public ResponseDto(Object data, String message, String timestamp, int status) {
        this.data = data;
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public ResponseDto() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto that = (ResponseDto) o;
        return status == that.status && Objects.equals(data, that.data) && Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, message, timestamp, status);
    }
}

