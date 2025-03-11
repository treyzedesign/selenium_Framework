package org.example.pom.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    private Properties prop;

    // Constructor to load properties file
    public ReadConfig() {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
        }
    }

    // Method to get property value by key
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
