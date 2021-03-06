package org.olegpash.server.interfaces;

import org.olegpash.common.exceptions.DatabaseException;

import java.sql.Connection;

public interface DBConnectable {
    void handleQuery(SQLConsumer<Connection> queryBody) throws DatabaseException;

    <T> T handleQuery(SQLFunction<Connection, T> queryBody) throws DatabaseException;
}
