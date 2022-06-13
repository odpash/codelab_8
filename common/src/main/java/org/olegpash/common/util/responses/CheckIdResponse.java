package org.olegpash.common.util.responses;


import org.olegpash.common.abstractions.AbstractResponse;

public class CheckIdResponse extends AbstractResponse {

    public CheckIdResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}