package org.olegpash.server.clientcommands;


import org.olegpash.common.entities.MusicBand;
import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class AddIfMinCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public AddIfMinCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("add_if_min",
                0,
                "add a new item to the collection if its value is less than the value of the minimum item in this collection");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            MusicBand bandToAdd = request.getBandArgument();
            if (collectionManager.checkMin(bandToAdd)) {
                Long id = dbManager.addElement(bandToAdd, request.getUsername());
                bandToAdd.setId(id);
                collectionManager.addMusicBand(bandToAdd);
                return new CommandResponse(true, "Element was successfully added to collection with ID: "
                        + id);
            } else {
                return new CommandResponse(false, "Element is not min");
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
