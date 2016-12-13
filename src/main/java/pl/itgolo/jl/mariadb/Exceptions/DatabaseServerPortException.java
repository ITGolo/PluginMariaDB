package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseServerPortException extends Exception {

    /**
     * Constructor
     */
    public DatabaseServerPortException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseServerPortException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseServerPortException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseServerPortException(String message, Throwable cause) {
        super (message, cause);
    }
}