package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.CollectionIsEmptyException;
import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class MinByStudioCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public MinByStudioCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("min_by_studio",
                0,
                "output any object from the collection whose studio field value is minimal");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            try {
                return new CommandResponse(true, "Minimal element:", collectionManager.returnMinByStudio());
            } catch (CollectionIsEmptyException e) {
                return new CommandResponse(false, e.getMessage());
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
