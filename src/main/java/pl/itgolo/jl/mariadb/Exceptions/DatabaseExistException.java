package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseExistException extends Exception {

    /**
     * Constructor
     */
    public DatabaseExistException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseExistException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseExistException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseExistException(String message, Throwable cause) {
        super (message, cause);
    }
}