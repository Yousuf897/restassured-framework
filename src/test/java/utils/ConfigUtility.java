package utils;

import config.ConfigLoader;

public class ConfigUtility {

    public static String getEmailDomain() {
        return ConfigLoader.getProperty("email.domain");
    }

    public static String getEnvironment() {
        return ConfigLoader.getProperty("environment").trim().toUpperCase();
    }

    public static int getTokenExpirationMinutes() {
        return ConfigLoader.getIntProperty("token.expiration.minutes");
    }

    public static int getTimeOuts() {
        return ConfigLoader.getIntProperty("timeouts");
    }

}
