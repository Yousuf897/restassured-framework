package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if ( input == null ) {
                throw new RuntimeException("Either you don't have config.properties or config.properties is empty! (src/test/resources/config/)");
            }
            properties.load(input);
        } catch (IOException e ) {
            throw new RuntimeException("Failed to load configuration from: config/config.properties");
        }
    }

    private static boolean checkIfPropertyIsNullOrIsEmpty(String key) {
        return properties.getProperty(key) == null ||  properties.getProperty(key).equals(" ");
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return ( checkIfPropertyIsNullOrIsEmpty(key) )
                ? 5000
                : Integer.parseInt(properties.getProperty(key));
    }

    public static Properties getProperties() {
        return properties;
    }

    public static boolean getBooleanProperty(String key) {
//        return Boolean.parseBoolean(properties.getProperty(key));
        return ( checkIfPropertyIsNullOrIsEmpty(key) )
                ? false
                : Boolean.parseBoolean(properties.getProperty(key));
    }

}
