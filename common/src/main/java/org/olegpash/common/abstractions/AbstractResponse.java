package org.olegpash.common.abstractions;

import java.io.Serializable;

public class AbstractResponse implements Serializable {

    private final boolean isSuccess;

    private final String message;

    private Long responseId;

    public AbstractResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Class<?> getType() {
        return this.getClass();
    }

    @Override
    public String toString() {
        return "AbstractResponse{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}