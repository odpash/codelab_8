package org.olegpash.server;


import org.olegpash.common.abstractions.AbstractRequest;
import org.olegpash.common.abstractions.AbstractResponse;
import org.olegpash.common.util.TextColoring;
import org.olegpash.common.util.requests.*;
import org.olegpash.common.util.responses.ConnectionResponse;
import org.olegpash.server.db.DBSSHConnector;
import org.olegpash.server.interfaces.SocketWorkerInterface;
import org.olegpash.server.util.CommandManager;
import org.olegpash.server.util.RequestWithAddress;
import org.olegpash.server.util.UsersManager;

import java.io.IOException;
import java.util.concurrent.*;


public class RequestThread implements Runnable {

    private final SocketWorkerInterface serverSocketWorker;
    private final CommandManager commandManager;
    private final UsersManager usersManager;
    private final ExecutorService fixedService = Executors.newFixedThreadPool(5);
    private final ExecutorService cachedService = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = new ForkJoinPool(4);

    public RequestThread(SocketWorkerInterface serverSocketWorker, CommandManager commandManager, UsersManager usersManager) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
        this.usersManager = usersManager;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            try {
                Future<RequestWithAddress> listenFuture = fixedService.submit(serverSocketWorker::listenForRequest);
                RequestWithAddress acceptedRequest = listenFuture.get();
                if (acceptedRequest != null) {
                    CompletableFuture
                            .supplyAsync(acceptedRequest::getRequest)
                            .thenApplyAsync(request -> {
                                AbstractResponse response = proceedRequest(request);
                                response.setResponseId(request.getRequestId());
                                return response;
                            }, cachedService)
                            .thenAcceptAsync(response -> {
                                try {
                                    serverSocketWorker.sendResponse(response, acceptedRequest.getSocketAddress());
                                } catch (IOException e) {
                                    ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
                                }
                            }, forkJoinPool);
                }
            } catch (ExecutionException e) {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            } catch (InterruptedException e) {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("An error occurred while deserializing the request, try again"));
            }
        }
        try {
            serverSocketWorker.stopSocketWorker();
            DBSSHConnector.closeSSH();
            fixedService.shutdown();
            cachedService.shutdown();
            forkJoinPool.shutdown();
        } catch (IOException e) {
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("An error occurred during stopping the server"));
        }
    }

    private AbstractResponse proceedRequest(AbstractRequest request) {
        if (request.getType().equals(CommandRequest.class)) {
            return commandManager.executeClientCommand((CommandRequest) request);
        } else if (request.getType().equals(RegisterRequest.class)) {
            return usersManager.registerNewUser((RegisterRequest) request);
        } else if (request.getType().equals(LoginRequest.class)) {
            return usersManager.loginUser((LoginRequest) request);
        } else if (request.getType().equals(CollectionRequest.class)) {
            return commandManager.returnCollection((CollectionRequest) request);
        } else if (request.getType().equals(CheckIdRequest.class)) {
            return commandManager.checkId((CheckIdRequest) request);
        } else {
            return new ConnectionResponse(true, "Connection is ok!");
        }
    }
}
