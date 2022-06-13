package org.olegpash.common.util.requests;


public class ConnectionRequest extends AbstractRequest {

    public ConnectionRequest(String clientInfo) {
        super(clientInfo);
    }

    @Override
    public String toString() {
        return "Connection request";
    }
}