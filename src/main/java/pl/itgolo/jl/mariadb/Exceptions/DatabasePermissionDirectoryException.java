package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabasePermissionDirectoryException extends Exception {

    /**
     * Constructor
     */
    public DatabasePermissionDirectoryException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabasePermissionDirectoryException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabasePermissionDirectoryException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabasePermissionDirectoryException(String message, Throwable cause) {
        super (message, cause);
    }
}