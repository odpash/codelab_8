package org.olegpash.server.interfaces;

import org.olegpash.common.abstractions.AbstractResponse;
import org.olegpash.server.util.RequestWithAddress;

import java.io.IOException;
import java.net.SocketAddress;

public interface SocketWorkerInterface {
    RequestWithAddress listenForRequest() throws IOException, ClassNotFoundException;

    void sendResponse(AbstractResponse response, SocketAddress address) throws IOException;

    void stopSocketWorker() throws IOException;
}
