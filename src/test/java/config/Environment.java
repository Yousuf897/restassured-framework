package config;

import utils.ConfigUtility;

public enum Environment {
    DEV("https://dev.bookstore.demoqa.com"),
    QA("https://qa.bookstore.demoqa.com"),
    PROD("https://bookstore.demoqa.com");

    private final String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public static Environment getCurrentEnvironment() {
       return Environment.valueOf(ConfigUtility.getEnvironment());
    }
}
