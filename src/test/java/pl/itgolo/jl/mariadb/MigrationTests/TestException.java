package pl.itgolo.jl.mariadb.MigrationTests;

import ch.vorburger.mariadb4j.DB;
import org.junit.Test;
import pl.itgolo.jl.mariadb.Exceptions.*;
import pl.itgolo.jl.mariadb.Manager;
import pl.itgolo.jl.mariadb.Server;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class TestException {

    static String resource = "pl/itgolo/jl/mariadb/Migrations/Examples";
    static Integer port = 7777;
    static String name = "myDatabase";
    static String username = "administrator";
    static String password = "root";
    static String path = "database";

    @Test(expected = DatabaseMigrationsException.class)
    public void test_run_server_with_exception_of_can_execute_migrations() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException, DatabasePermissionDirectoryException, DatabaseServerStartException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabaseSuperAdminException, DatabaseMigrationsException, DatabaseCreateException, IOException {
        Manager.deleteAllDatabases(path);
        DB db = Server.start(path, port, name, username, password, resource, false);
    }
}
