package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import pl.itgolo.jl.mariadb.Exceptions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class Database {

    /**
     * Create database
     *
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param db       database instance of vorburger
     * @throws DatabaseCreateException failed create or check exist database
     */
    public static void createOrExist(String name, String username, String password, DB db) throws DatabaseCreateException {
        if (name.equals("test")) {
            throw new IllegalArgumentException("Can't be name database 'test'.");
        }
        try {
            db.run("create database if not exists `" + name + "`;", username, password, null);
        } catch (ManagedProcessException e) {
            throw new DatabaseCreateException(String.format("Failed create database. Name: %1$s, username: %2$s, password: %3$s.", name, username, password), e);
        }
    }

    /**
     * Get version database
     *
     * @param connection SQL connection
     * @param address  address to database (ip/host)
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @return version of database
     * @throws DatabaseVersionException    failed get version from table 'schema_version'
     * @throws DatabaseConnectionException failed connection to database
     */
    public static String getVersion(Connection connection, String address, Integer port, String name, String username, String password) throws DatabaseVersionException, DatabaseConnectionException {
        QueryRunner queryRunner = new QueryRunner();
        List results;
        try {
            results = queryRunner.query(connection, "select * from schema_version ORDER BY installed_rank DESC", new MapListHandler());
        } catch (SQLException e) {
            throw new DatabaseVersionException(String.format("Failed get version database. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", address, port, name, username, password), e);
        }
        if (results.size() == 0) {
            throw new DatabaseVersionException(String.format("Zero rows in table 'schema_version'. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", address, port, name, username, password));
        }
        Object version = ((Map) results.get(0)).get("Version");
        if (Objects.isNull(version)) {
            throw new DatabaseVersionException(String.format("Get row from table 'schema_version' is null. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", address, port, name, username, password));
        }
        if (version.toString().equals("")) {
            throw new DatabaseVersionException(String.format("Get row from table 'schema_version' is empty. Address: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", address, port, name, username, password));
        }
        return version.toString();
    }
}
