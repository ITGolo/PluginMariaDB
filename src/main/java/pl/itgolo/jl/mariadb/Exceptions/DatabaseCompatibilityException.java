package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseCompatibilityException extends Exception {

    /**
     * Constructor
     */
    public DatabaseCompatibilityException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseCompatibilityException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseCompatibilityException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseCompatibilityException(String message, Throwable cause) {
        super (message, cause);
    }
}