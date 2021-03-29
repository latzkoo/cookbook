package hu.latzkoo.cookbook.config;

import java.io.IOException;
import java.util.Properties;

public class Config {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getResourceAsStream("/application.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String value) {
        return properties.getProperty(value);
    }

}
