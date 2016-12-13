package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.itgolo.jl.mariadb.Exceptions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class TestConnection {

    static String address = "localhost";
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

    @Test(expected = DatabaseConnectionException.class)
    public void test_check_no_running_server_mariadb() throws DatabaseConnectionException, DatabaseUnknownException, DatabaseExistException, DatabaseAuthorizationException {
        Connector.open("incorrect_address", port, name, username, password);
    }

    @Test(expected = DatabaseExistException.class)
    public void test_check_exist_database() throws DatabaseConnectionException, DatabaseUnknownException, IOException, ManagedProcessException, DatabaseExistException, DatabaseAuthorizationException {
        Connector.open(address, port, "incorrect_name", username, password);
    }

    @Test(expected = DatabaseAuthorizationException.class)
    public void test_connection_to_database_with_incorrect_username() throws IOException, ManagedProcessException, DatabaseUnknownException, DatabaseExistException, DatabaseConnectionException, SQLException, DatabaseAuthorizationException {
       Connector.open(address, port, name, "incorrect_username", password);
    }

    @Test
    public void test_check_connection_to_database_by_method_is_exist(){
        Boolean connected = Connector.isCorrect("incorrect_address", port, name, username, password);
        Assert.assertFalse(connected);
    }

    @Test
    public void test_check_running_server_mariadb() throws IOException, ManagedProcessException, SQLException {
        Boolean connected = Connector.isCorrect(address, port, name, username, password);
        Assert.assertTrue(connected);
    }

    @Test
    public void test_connection_to_database_with_correct_username() throws IOException, ManagedProcessException, DatabaseUnknownException, DatabaseExistException, DatabaseConnectionException, SQLException, DatabaseAuthorizationException {
        Connection connection = Connector.open(address, port, name, username, password);
        QueryRunner queryRunner = new QueryRunner();

        List results = queryRunner.query(connection, "select user, password, host from mysql.user", new MapListHandler());
        results.forEach(result->{
            Map map = (Map) result;
            System.out.println(String.format("user: %1$s, password: %2$s, host: %3$s", map.get("user"), map.get("password"), map.get("host")));
        });
    }
}
