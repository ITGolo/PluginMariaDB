package pl.itgolo.jl.mariadb;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by ITGolo on 12.12.2016.
 */
public class Manager {

    /**
     * Delete force directory with all databases
     * @param path path to directory with all databases
     * @throws IOException failed delete directory
     */
    public static void deleteAllDatabases(String path) throws IOException {
        FileUtils.deleteQuietly(new File(path));
    }
}
