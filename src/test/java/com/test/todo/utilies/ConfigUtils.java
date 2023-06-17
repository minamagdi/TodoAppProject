package com.test.todo.utilies;

import java.util.Properties;

public class ConfigUtils {
    private static ConfigUtils configUtils;
    private Properties properties;

    private ConfigUtils() {
        String env = System.getProperty("env", "Production");
        switch (env) {
            case "Production" ->
                    properties = PropertiesUtils.loadProperties("src/test/java/com/test/todo/config/Production.properties");
            case "local" ->
                    properties = PropertiesUtils.loadProperties("src/test/java/com/test/todo/config/local.properties");
            default -> throw new RuntimeException("Environment not supported");
        }
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;

    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        throw new RuntimeException("cant find the url in the property");

    }

    public String getEmail() {
        String prop = properties.getProperty("email");
        if (prop != null) return prop;
        throw new RuntimeException("cant find the email in the property");

    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) return prop;
        throw new RuntimeException("cant find the password in the property");

    }
}
