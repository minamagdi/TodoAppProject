package com.test.todo.utilies;

import java.io.*;
import java.util.Properties;


public class PropertiesUtils {
    public static Properties properties;

    public static Properties loadProperties(String filePath) {
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file is not found");
        } catch (IOException e) {
            throw new RuntimeException("error while loading properties");
        }
    }
}
