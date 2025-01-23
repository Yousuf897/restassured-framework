package config;

public enum Environment {
    DEV("https://bookstore.demoqa.com"),
    QA("https://bookstore.demoqa.com"),
    PROD("https://bookstore.demoqa.com");

    private final String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public static Environment getCurrentEnvironment() {
       String env = ConfigLoader.getProperty("environment").toUpperCase();
       return Environment.valueOf(env);
    }
}
