package org.olegpash.common.util.responses;


import org.olegpash.common.abstractions.AbstractResponse;

public class ConnectionResponse extends AbstractResponse {
    public ConnectionResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}