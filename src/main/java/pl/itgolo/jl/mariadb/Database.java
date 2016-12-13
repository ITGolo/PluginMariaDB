package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseCreateException;

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
     * @param db   database instance of vorburger
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
}
