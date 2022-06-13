package org.olegpash.common.util.requests;


import org.olegpash.common.abstractions.AbstractRequest;

public class ConnectionRequest extends AbstractRequest {

    public ConnectionRequest(String clientInfo) {
        super(clientInfo);
    }

    @Override
    public String toString() {
        return "Connection request";
    }
}