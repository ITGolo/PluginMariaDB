package pl.itgolo.jl.mariadb;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class Migration {

    /**
     * Migrate database
     *
     * @param address  address to database (ip/host)
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param resource classpath to directory with migration files
     */
    public static void migrate(String address, Integer port, String name, String username, String password, String resource) {
        resource = "classpath:" + resource;
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s/%3$s", address, port, name);
        Flyway flyway = new Flyway();
        flyway.setLocations(resource);
        flyway.setDataSource(connectionString, username, password);
        flyway.migrate();
    }

    /**
     * Clean migrations database
     *
     * @param address  address to database (ip/host)
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param resource classpath to directory with migration files
     */
    public static void clean(String address, Integer port, String name, String username, String password, String resource) {
        resource = "classpath:" + resource;
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s/%3$s", address, port, name);
        Flyway flyway = new Flyway();
        flyway.setLocations(resource);
        flyway.setDataSource(connectionString, username, password);
        flyway.clean();
    }

    /**
     * Validate migrations database
     *
     * @param address  address to database (ip/host)
     * @param port     port of server database
     * @param name     name to database
     * @param username username to database
     * @param password password to database
     * @param resource classpath to directory with migration files
     * @return true if correct validate or false if not correct validate
     */
    public static Boolean validate(String address, Integer port, String name, String username, String password, String resource) {
        resource = "classpath:" + resource;
        String connectionString = String.format("jdbc:mysql://%1$s:%2$s/%3$s", address, port, name);
        Flyway flyway = new Flyway();
        flyway.setLocations(resource);
        flyway.setDataSource(connectionString, username, password);
        try {
            flyway.validate();
            return true;
        } catch (FlywayException e){
            return false;
        }
    }
}
