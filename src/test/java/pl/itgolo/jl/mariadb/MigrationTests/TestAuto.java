package pl.itgolo.jl.mariadb.MigrationTests;

import ch.vorburger.mariadb4j.DB;
import org.junit.Assert;
import org.junit.Test;
import pl.itgolo.jl.mariadb.Exceptions.*;
import pl.itgolo.jl.mariadb.Manager;
import pl.itgolo.jl.mariadb.Migration;
import pl.itgolo.jl.mariadb.Server;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class TestAuto {

    static String resource = "pl/itgolo/jl/mariadb/Migrations/Examples";
    static String address = "localhost";
    static Integer port = 7777;
    static String name = "myDatabase";
    static String username = "administrator";
    static String password = "root";
    static String path = "database";

    @Test
    public void test_run_server_with_auto_migrations() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException, DatabasePermissionDirectoryException, DatabaseServerStartException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabaseSuperAdminException, DatabaseMigrationsException, DatabaseCreateException, IOException {
        Manager.deleteAllDatabases(path);
        DB db = Server.start(path, port, name, username, password, resource, true);
        Boolean validate = Migration.validate(address, port, name, username, password, resource);
        Assert.assertTrue(validate);
    }
}
