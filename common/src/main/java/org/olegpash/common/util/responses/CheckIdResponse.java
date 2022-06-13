package org.olegpash.common.util.responses;


public class CheckIdResponse extends AbstractResponse {

    public CheckIdResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}