package org.olegpash.server;


import org.olegpash.server.util.CommandManager;
import org.olegpash.server.util.ServerCommandListener;

public class ConsoleThread implements Runnable {

    private final ServerCommandListener serverCommandListener;
    private final CommandManager commandManager;

    public ConsoleThread(ServerCommandListener serverCommandListener, CommandManager commandManager) {
        this.serverCommandListener = serverCommandListener;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            String command = serverCommandListener.readCommand();
            ServerConfig.getConsoleTextPrinter().printlnText(commandManager.executeServerCommand(command));
        }
    }

}
