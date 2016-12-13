package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseVersionException extends Exception {

    /**
     * Constructor
     */
    public DatabaseVersionException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseVersionException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseVersionException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseVersionException(String message, Throwable cause) {
        super (message, cause);
    }
}