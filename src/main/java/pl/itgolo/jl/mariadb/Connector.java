package pl.itgolo.jl.mariadb;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.itgolo.jl.mariadb.Exceptions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class Connector {

    /* logger */
    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    /**
     * Create and open connection SQL
     *
     * @param address  address to database (ip/host)
     * @param port     port to database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param version  version application
     * @param validateVersion validate version application to version database
     * @return connection to database
     * @throws DatabaseConnectionException    failed connection to server database
     * @throws DatabaseUnknownException       unknown failed connection to server database
     * @throws DatabaseExistException         database not exist
     * @throws DatabaseAuthorizationException failed authorization to database
     * @throws DatabaseVersionException    failed get version from table 'schema_version'
     * @throws DatabaseCompatibilityException no compatibility versions database
     */
    public static Connection open(String address, Integer port, String name, String username, String password, String version, Boolean validateVersion) throws DatabaseConnectionException, DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseVersionException, DatabaseCompatibilityException {
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s/%3$s", address, port, name);
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            if (validateVersion){
                String versionDatabase = Database.getVersion(connection, address, port, name, username, password);
                if (version.equals(versionDatabase) == false){
                    throw new DatabaseCompatibilityException(String.format("No compatibility versions database. Version application: %1$s, version database: %2$s, address: %3$s, port: %4$s, name: %5$s, username: %6$s, password: %7$s.", version, versionDatabase, address, port, name, username, password));
                }
            }
            return connection;
        } catch (SQLException e) {
            DistributorSQLExceptions.connection(e, address, port, name, username, password);
        }
        return null;
    }

    /**
     * Check connection is correct to database
     *
     * @param address  address to database (ip/host)
     * @param port     port to database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @return true if connected or false if not connected
     */
    public static Boolean isCorrect(String address, Integer port, String name, String username, String password) {
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s/%3$s", address, port, name);
        Connection connection = null;
        Boolean connected = false;
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            connected = true;
        } catch (SQLException e) {
            // this method not require service exception, because return boolean type for correct connection
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return connected;
    }

    /**
     * Check connection is correct to database
     *
     * @param address  address to database (ip/host)
     * @param port     port to database
     * @param username username to database
     * @param password password to database
     * @return true if connected or false if not connected
     */
    public static Boolean isCorrect(String address, Integer port, String username, String password) {
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s", address, port);
        Connection connection = null;
        Boolean connected = false;
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            connected = true;
        } catch (SQLException e) {
            // this method not require service exception, because return boolean type for correct connection
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return connected;
    }
}
