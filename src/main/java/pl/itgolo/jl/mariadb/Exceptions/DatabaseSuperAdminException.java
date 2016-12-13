package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseSuperAdminException extends Exception {

    /**
     * Constructor
     */
    public DatabaseSuperAdminException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseSuperAdminException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseSuperAdminException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseSuperAdminException(String message, Throwable cause) {
        super (message, cause);
    }
}