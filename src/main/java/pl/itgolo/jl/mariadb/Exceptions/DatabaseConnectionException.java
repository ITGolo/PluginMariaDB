package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseConnectionException extends Exception {

    /**
     * Constructor
     */
    public DatabaseConnectionException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseConnectionException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseConnectionException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super (message, cause);
    }
}