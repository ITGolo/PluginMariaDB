package pl.itgolo.jl.mariadb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseAuthorizationException;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseConnectionException;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseExistException;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseUnknownException;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class Distributor {

    /* logger */
    private static final Logger logger = LoggerFactory.getLogger(Distributor.class);

    /**
     * Call the appropriate exception of connection
     *
     * @param address  address to database (ip/host)
     * @param port     port to database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @throws DatabaseConnectionException failed connection to server database
     * @throws DatabaseUnknownException unknown failed connection to database
     * @throws DatabaseExistException database not exist
     * @throws DatabaseAuthorizationException failed authorization to database
     */
    public static void connection(Throwable e, String address, Integer port, String name, String username, String password) throws DatabaseConnectionException, DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException {
        if (e instanceof SQLNonTransientConnectionException && ((SQLNonTransientConnectionException) e).getSQLState().equals("08")) { // no connect to database
            throw new DatabaseConnectionException(String.format("Could not connect to database. Address: %1$s, port: %2$s. SQL code error: %3$s", address, port, ((SQLNonTransientConnectionException) e).getErrorCode()), e);
        } else if (e instanceof SQLSyntaxErrorException && ((SQLSyntaxErrorException) e).getSQLState().equals("42000")) {
            throw new DatabaseExistException(String.format("Database not exist. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password %5$s.", address, port, name, username, password), e);
        } else if (e instanceof SQLInvalidAuthorizationSpecException && ((SQLInvalidAuthorizationSpecException) e).getSQLState().equals("28000")) {
            throw new DatabaseAuthorizationException(String.format("Failed authorization to database. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password %5$s.", address, port, name, username, password), e);
        } else {
            throw new DatabaseUnknownException(String.format("Unknown failed connection to database. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password %5$s.", address, port, name, username, password), e);
        }


    }
}
