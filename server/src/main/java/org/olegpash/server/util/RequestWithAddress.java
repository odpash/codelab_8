package org.olegpash.server.util;


import org.olegpash.common.abstractions.AbstractRequest;

import java.net.SocketAddress;

public class RequestWithAddress {
    private final AbstractRequest request;
    private final SocketAddress socketAddress;

    public RequestWithAddress(AbstractRequest request, SocketAddress socketAddress) {
        this.request = request;
        this.socketAddress = socketAddress;
    }

    public AbstractRequest getRequest() {
        return request;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
