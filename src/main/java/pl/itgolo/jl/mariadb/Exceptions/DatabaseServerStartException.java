package pl.itgolo.jl.mariadb.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 11.11.2016
 * Time: 20:43
 */
public class DatabaseServerStartException extends Exception {

    /**
     * Constructor
     */
    public DatabaseServerStartException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public DatabaseServerStartException(String message) {
        super (message);
    }

    /**
     * Constructor
     * @param cause cause exception
     */
    public DatabaseServerStartException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public DatabaseServerStartException(String message, Throwable cause) {
        super (message, cause);
    }
}