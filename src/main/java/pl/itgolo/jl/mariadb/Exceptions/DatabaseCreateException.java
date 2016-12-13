package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseCreateException extends Exception {

    /**
     * Constructor
     */
    public DatabaseCreateException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseCreateException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseCreateException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseCreateException(String message, Throwable cause) {
        super (message, cause);
    }
}