package org.olegpash.server.servercommands;


import org.olegpash.common.util.TextColoring;
import org.olegpash.server.ServerConfig;
import org.olegpash.server.abstractions.AbstractServerCommand;

public class ServerExitCommand extends AbstractServerCommand {

    public ServerExitCommand() {
        super("exit", "shut downs the server");
    }

    @Override
    public String executeServerCommand() {
        ServerConfig.toggleRun();
        return TextColoring.getGreenText("Server shutdown");
    }
}
