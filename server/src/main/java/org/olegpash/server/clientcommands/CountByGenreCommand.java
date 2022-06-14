package org.olegpash.server.clientcommands;


import org.olegpash.common.entities.MusicBand;
import org.olegpash.common.entities.enums.MusicGenre;
import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class CountByGenreCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public CountByGenreCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("count_by_genre",
                0,
                "returns count of elements, that's genre value equals input value");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            MusicGenre genre = request.getBandArgument().getGenre();
            return new CommandResponse(true, "Equals this genre count: " + collectionManager.genreCount(genre));

        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
