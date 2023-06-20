package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (InputStream is = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }
}
