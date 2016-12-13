package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseServerDoubleRunningException extends Exception {

    /**
     * Constructor
     */
    public DatabaseServerDoubleRunningException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseServerDoubleRunningException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseServerDoubleRunningException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseServerDoubleRunningException(String message, Throwable cause) {
        super (message, cause);
    }
}