package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseMigrationsException extends Exception {

    /**
     * Constructor
     */
    public DatabaseMigrationsException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseMigrationsException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseMigrationsException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseMigrationsException(String message, Throwable cause) {
        super (message, cause);
    }
}