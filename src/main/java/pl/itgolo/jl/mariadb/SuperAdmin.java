package pl.itgolo.jl.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import pl.itgolo.jl.mariadb.Exceptions.DatabaseSuperAdminException;

/**
 * Created by ITGolo on 13.12.2016.
 */
public class SuperAdmin {

    /**
     * Set default super admin authorization
     *
     * @param port     port to database
     * @param username username to database
     * @param password password to database
     * @param db       port of server database
     * @throws DatabaseSuperAdminException failed set default super admin authorization or username is 'root'
     */
    public static void setDefault(Integer port, String username, String password, DB db) throws DatabaseSuperAdminException {
        if (username.equals("root")){
            throw new DatabaseSuperAdminException("Username can not be 'root'");
        }
        if (Connector.isCorrect("localhost", port, "root", null)) {
            String command = "CREATE USER '" + username + "'@'%' IDENTIFIED BY '" + password + "'; GRANT ALL PRIVILEGES ON *.* TO '" + username + "'@'%' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0; DELETE FROM mysql.user WHERE user='root';";
            try {
                db.run(command, "root", null, null);
            } catch (ManagedProcessException e) {
                throw new DatabaseSuperAdminException(String.format("Failed set default super admin authorization. Command SQL: %1$s.", command), e);
            }
        }
    }
}
