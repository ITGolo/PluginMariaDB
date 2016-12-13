package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseCreateException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class TestEmbedded {

    static String address = "localhost";
    static Integer port = 7777;
    static String name = "myDatabase";
    static String username = "root2";
    static String password = "secret";
    static String path = "database";

    @Test
    public void test_embedded() throws ManagedProcessException, SQLException, IOException, DatabaseCreateException {
        FileUtils.forceDelete(new File("database"));
        DBConfigurationBuilder dbConfigurationBuilder = DBConfigurationBuilder.newBuilder();
        dbConfigurationBuilder.setPort(7744);
        dbConfigurationBuilder.setDataDir("database");
        DB db = DB.newEmbeddedDB(dbConfigurationBuilder.build());
        db.start();
        Database.createOrExist(name, null, null, db);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbConfigurationBuilder.getURL(name), "root", "");
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, "CREATE TABLE hello(world VARCHAR(100))");
            queryRunner.update(connection, "INSERT hello VALUES ('Hello, world')");
            List<String> results = queryRunner.query(connection, "SELECT * FROM hello", new ColumnListHandler<String>());
            Assert.assertEquals(1, results.size());
            Assert.assertEquals("Hello, world", results.get(0));
            System.out.println(results.get(0));
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
