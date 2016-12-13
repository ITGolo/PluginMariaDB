package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.itgolo.jl.mariadb.Exceptions.*;

import java.io.IOException;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class TestServer {

    static Integer port = 7777;
    static String name = "myDatabase";
    static String username = "administrator";
    static String password = "root";
    static String path = "database";

    @BeforeClass
    public static void setUp() throws ManagedProcessException, IOException, DatabaseSuperAdminException, DatabaseServerStartException, DatabaseCreateException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabasePermissionDirectoryException {
        Manager.deleteAllDatabases(path);
        DB db = Server.start(path, port, name, username, password);
    }

    @Test(expected = DatabaseServerPortException.class)
    public void test_run_server_on_open_port() throws DatabaseCreateException, DatabaseSuperAdminException, DatabaseServerStartException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabasePermissionDirectoryException {
        Server.start(path, port, "other_name", username, password);
    }

    @Test(expected = DatabaseServerDoubleRunningException.class)
    public void test_run_double_server() throws DatabaseServerPortException, DatabaseCreateException, DatabaseSuperAdminException, DatabaseServerStartException, DatabaseServerDoubleRunningException, DatabasePermissionDirectoryException {
        Server.start(path, port, name, username, password);
    }
}
