package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseUnknownException extends Exception {

    /**
     * Constructor
     */
    public DatabaseUnknownException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseUnknownException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseUnknownException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseUnknownException(String message, Throwable cause) {
        super (message, cause);
    }
}