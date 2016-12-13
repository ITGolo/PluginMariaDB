package pl.itgolo.jl.mariadb.MigrationTests;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.itgolo.jl.mariadb.*;
import pl.itgolo.jl.mariadb.Exceptions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class TestMigration {

    static String resource = "pl/itgolo/jl/mariadb/Migrations/Examples";
    static String address = "localhost";
    static Integer port = 7777;
    static String name = "myDatabase";
    static String username = "administrator";
    static String password = "root";
    static String path = "database";

    @BeforeClass
    public static void setUp() throws ManagedProcessException, IOException, DatabaseSuperAdminException, DatabaseServerStartException, DatabaseCreateException, DatabaseServerPortException, DatabaseServerDoubleRunningException, DatabasePermissionDirectoryException, DatabaseMigrationsException {
        Manager.deleteAllDatabases(path);
        DB db = Server.start(path, port, name, username, password, resource, true);
    }

    @Test
    public void test_migration_of_flyway() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException, DatabaseMigrationsException, DatabaseCompatibilityException {
        Migration.migrate(address, port, name, username, password, resource);
        Connection connection = Connector.open(address, port, name, username, password, null, false);
        QueryRunner queryRunner = new QueryRunner();
        List results = queryRunner.query(connection, "select * from examples", new MapListHandler());
        Assert.assertEquals("example", ((Map) results.get(0)).get("example"));
    }

    @Test(expected = DatabaseVersionException.class)
    public void test_get_last_version_migration_executed_without_exist_schema_version_table() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException, DatabaseMigrationsException, DatabaseCompatibilityException {
        Migration.clean(address, port, name, username, password, resource);
        Connection connection = Connector.open(address, port, name, username, password, null, false);
        Database.getVersion(connection, address, port, name, username, password);
        Migration.migrate(address, port, name, username, password, resource);
    }

    @Test
    public void test_get_last_version_migration_executed() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException, DatabaseMigrationsException, DatabaseCompatibilityException {
        Migration.clean(address, port, name, username, password, resource);
        Migration.migrate(address, port, name, username, password, resource);
        Connection connection = Connector.open(address, port, name, username, password, null, false);
        String version = Database.getVersion(connection, address, port, name, username, password);
        Assert.assertEquals(version, "1.0.0.1");
    }

    @Test
    public void test_validate_incorrect_migrations() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException {
        Migration.clean(address, port, name, username, password, resource);
        Boolean validate = Migration.validate(address, port, name, username, password, resource);
        Assert.assertFalse(validate);
        //Migration.migrate(address, port, name, username, password, resource);
        //String version = Database.getVersion(address, port, name, username, password);
        //Assert.assertEquals(version, "1.0.0.1");
    }

    @Test
    public void test_validate_migrations() throws DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException, DatabaseConnectionException, SQLException, DatabaseVersionException {
        Migration.clean(address, port, name, username, password, resource);
        Migration.migrate(address, port, name, username, password, resource);
        Boolean validate = Migration.validate(address, port, name, username, password, resource);
        Assert.assertTrue(validate);
    }
}
