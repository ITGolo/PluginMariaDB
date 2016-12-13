package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.itgolo.jl.mariadb.Exceptions.*;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class Server {

    /* logger */
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * Start server database
     *
     * @param path     path to directory with all databases
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @return db instance of vorburger
     * @throws DatabaseServerStartException         failed build database configuration or failed start server
     * @throws DatabaseSuperAdminException          failed set default super admin authorization or username is 'root'
     * @throws DatabaseCreateException              failed create or check exist database
     * @throws DatabaseServerPortException          port is busy for localhost address
     * @throws DatabaseServerDoubleRunningException attempt double running server
     * @throws DatabasePermissionDirectoryException failed permission to path
     */
    public static DB start(String path, Integer port, String name, String username, String password) throws DatabaseServerStartException, DatabaseSuperAdminException, DatabaseCreateException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabasePermissionDirectoryException {
        if (isLaunched(port, name, username, password)) {
            throw new DatabaseServerDoubleRunningException(String.format("Server is already running. Path: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", path, port, name, username, password));
        }
        isFreePort(port);
        isWritable(path);
        DBConfigurationBuilder dbConfigurationBuilder = DBConfigurationBuilder.newBuilder();
        dbConfigurationBuilder.setPort(port);
        dbConfigurationBuilder.setDataDir(path);
        dbConfigurationBuilder.addArg("--skip-grant-tables=false");
        DB db;
        try {
            db = DB.newEmbeddedDB(dbConfigurationBuilder.build());
            db.start();
        } catch (ManagedProcessException e) {
            throw new DatabaseServerStartException(String.format("Failed start server. Path: %1$s, port: %2$s, name: %3$s, username: %4$s, password: %5$s.", path, port, name, username, password), e);
        }
        setDefaultOrExist(port, name, username, password, db);
        return db;
    }

    /**
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param db       port of server database
     * @throws DatabaseSuperAdminException failed set default super admin authorization or username is 'root'
     * @throws DatabaseCreateException     failed create or check exist database
     */
    public static void setDefaultOrExist(Integer port, String name, String username, String password, DB db) throws DatabaseSuperAdminException, DatabaseCreateException {
        SuperAdmin.setDefault(port, username, password, db);
        Database.createOrExist(name, username, password, db);
    }

    /**
     * Check is free port
     *
     * @param port port of server database
     * @throws DatabaseServerPortException port is busy for localhost address
     */
    public static void isFreePort(Integer port) throws DatabaseServerPortException {
        Socket socket = null;
        try {
            socket = SocketFactory.getDefault().createSocket("localhost", port);
            if (socket.isConnected()) {
                throw new DatabaseServerPortException(String.format("Port %1$s is busy for address: 'localhost'.", port));
            }
        } catch (IOException e) {
            // service exception is not require because this method only check free port
        } finally {
            try {
                if (Objects.nonNull(socket)) {
                    socket.close();
                }
            } catch (IOException e) {
                // service exception is not require because this exception it isn't need
            }
        }
    }

    /**
     * Check server is launched
     *
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @return true if server is launched or false if server is not launched
     */
    public static Boolean isLaunched(Integer port, String name, String username, String password) {
        return Connector.isCorrect("localhost", port, name, username, password);
    }

    /**
     * Check path is writable
     *
     * @param path path to directory with all databases
     * @throws DatabasePermissionDirectoryException failed permission to path
     */
    public static void isWritable(String path) throws DatabasePermissionDirectoryException {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            throw new DatabasePermissionDirectoryException(String.format("Failed permission. Can not be create directory for path: %1$s.", path), e);
        }
    }
}
